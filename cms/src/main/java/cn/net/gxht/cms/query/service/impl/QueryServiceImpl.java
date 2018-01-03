package cn.net.gxht.cms.query.service.impl;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.query.dao.QueryDao;
import cn.net.gxht.cms.query.entity.Iphone;
import cn.net.gxht.cms.query.entity.QueryInfo;
import cn.net.gxht.cms.query.entity.QueryPhone;
import cn.net.gxht.cms.query.entity.QueryUserInfo;
import cn.net.gxht.cms.query.service.QueryService;
import cn.net.gxht.cms.user.entity.GetTest;
import cn.net.gxht.cms.user.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/17.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class QueryServiceImpl implements QueryService {
    @Resource
    private QueryDao queryDao;

    public JSONObject getQueryInfo(QueryUserInfo queryUserInfo) throws IOException {
        StringBuilder honeyUrl = new StringBuilder("https://way.jd.com/juxinli/henypot4JD");
        StringBuilder authUrl = new StringBuilder("https://way.jd.com/youhuoBeijing/test");
        StringBuilder p2pUrl = new StringBuilder("https://way.jd.com/huiyutech/p2p_user");
        StringBuilder railWayUrl = new StringBuilder("https://way.jd.com/cloudatamix/checktrade");
        StringBuilder debitAndCreditUrl = new StringBuilder("https://way.jd.com/hxdcdk/dtjd");
        Map<String, Object> urlMap = new HashedMap();
        urlMap.put("honey", honeyUrl);
        urlMap.put("auth", authUrl);
        urlMap.put("p2p", p2pUrl);
        urlMap.put("railWay", railWayUrl);
        urlMap.put("debitAndCredit", debitAndCreditUrl);
        //遍历urlMap,获得5个jsonObject将5个jsonObject合为一个jsonObject
        Map<String, Object> queryMap = QueryInfo.getMapFromObject(queryUserInfo);
        JSONObject resultJson = new JSONObject();
        for (String key : urlMap.keySet()) {
            JSONObject jsonObject = GetTest.sendGetReturnJsonString(queryMap, (StringBuilder) urlMap.get(key));
            System.out.println(jsonObject.toJSONString());
            resultJson.put(key, jsonObject);
        }
        System.out.println(resultJson);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = simpleDateFormat.format(date);
        Md5Hash md5Hash = new Md5Hash(queryUserInfo.toString());
        String objectFilePrefix = md5Hash.toString();
        String objectSavePath = File.separator + "mydata" + File.separator + "queryInfo" + File.separator + now + File.separator + objectFilePrefix + ".txt";
        saveObjectFile(objectSavePath, resultJson);
        //将基本信息保存至数据库
        String createBy = getCurrentUser();
        queryUserInfo.setInfoFilePath(objectSavePath);
        Integer i = queryDao.insertQueryInfo(queryUserInfo, createBy);
        if (i < 0) {
            throw new RuntimeException("插入数据异常");
        }
        return resultJson;
    }

    @Override
    public Map<String, Object> findQueryRecord(Page page, String keyword) {
        String name = "";
        String phone = "";
        String idCard = "";
        if (keyword != null) {
            String[] param = keyword.split(",");
            if (param.length == 3) {
                name = param[0];
                phone = param[1];
                idCard = param[2];
            } else if (param.length == 2) {
                name = param[0];
                phone = param[1];
            } else if (param.length == 1) {
                name = param[0];
            }
        }
        page.setStartIndex(page.getStartIndex());
        QueryUserInfo[] queryUserInfos = queryDao.findUserQueryRecord(page, null, name, phone, idCard);
        page.setAmount(queryDao.getAmount());
        page.setEndIndex(page.getEndIndex());
        page.setAllPage();
        Map<String, Object> queryInfoMap = new HashedMap();
        queryInfoMap.put("pageInfo", queryUserInfos);
        queryInfoMap.put("pageObject", page);
        return queryInfoMap;
    }

    //读取对象文件,当用户查询完之后还要查看之前查询人的信息就不用发送请求区查看，之间将之前保存的对象文件读取
    public JSONObject readObjectFile(Integer id) throws IOException, ClassNotFoundException {
        //根据id找到path
        Page page = new Page();
        QueryUserInfo[] queryUserInfos = queryDao.findUserQueryRecord(page, id, null, null, null);
        String path = queryUserInfos[0].getInfoFilePath();
        //根据path找到文件的位置
        File file = new File(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        JSONObject jsonObject = (JSONObject) objectInputStream.readObject();
        objectInputStream.close();
        return jsonObject;
    }

    //保存对象文件，可以和保存查询信息下载一起
    public void saveObjectFile(String path, JSONObject jsonObject) throws IOException {
        File file = new File(path);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        file.createNewFile();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(jsonObject);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public String getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("currentUser");
        String userName = user.getName();
        return userName;
    }

    /**
     * 获取序列号/IMEI码查询结果
     *
     * @param number 序列号或IMEI码
     */
    @Override
    public String getSericaAndIEMIInfo(String number) {
        String path="";
        //获取查询返回json字符串
        // String jsonStr=getResult(number);
        //测试用
        String jsonStr="{\"code\":0,\"data\":{\"imei\":\"\",\"sn\":\"F2LV689YJCLP\",\"model\":\"iPhone 8 Plus\",\"storage\":\"256GB\",\"color\":\"Gold\",\"status\":\"normal\",\"activated\":\"\",\"purchase\":{\"date\":\"2017-09-30\",\"validated\":\"\",\"estimated\":\"2017-09-30\",\"country\":\"\"},\"coverage\":\"2018-09-29\",\"daysleft\":276,\"support\":\"2017-12-29\",\"applecare\":false,\"repair\":\"none\",\"sales\":\"DS01\",\"fmi\":\"\",\"icloud\":\"\",\"loaner\":\"N\",\"manufacture\":{\"date\":\"2017-08-05\",\"factory\":\"中国（郑州）\"},\"img\":\"http://acs.3023.com/apple/zrup.png\"}}";
        try{
            //存到.txt文件并返回存储路径
            path=saveResultToFile(number,jsonStr);
        }catch (IOException e){
            throw  new RuntimeException("文件存储失败");
        }
        QueryPhone queryPhone=new QueryPhone();
        queryPhone.setSn(number);
        queryPhone.setQueryBy(getCurrentUser());
        queryPhone.setInfoFilePath(path);
        //将查询对象存储至数据库
        saveResultToMySql(queryPhone);
        return jsonStr;
    }

    /**
     * 通过序列号/IMEI查询历史记录
     * @param sn
     * @return 查询记录集合
     */
    @Override
    public Map<String, Object> findQueryPhone(Page page,String sn) {
        page.setStartIndex(page.getStartIndex());
        List<QueryPhone> listPhones=queryDao.findPhoneQueryRecord(page,sn);
        page.setPhoneamount(queryDao.getPhoneAmount(sn));
        page.setEndIndex(page.getEndIndex());
        page.setAllPage();
        System.out.println(page.toString());
        Map<String, Object> queryInfoMap = new HashedMap();
        queryInfoMap.put("pageInfo", listPhones);
        queryInfoMap.put("pageObject", page);
        return queryInfoMap;
    }

    //苹果查询实现方法
    private String getResult(String sn) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        //苹果序列号/IMEI接口地址
        String strUrl = "https://api.3023.com/apple/appraisal?repair=true&fmi=true&sn=" + sn;
        try {
            URL e = new URL(strUrl);
            StringBuffer sb = new StringBuffer();
            conn = (HttpURLConnection) e.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36");
            conn.setRequestProperty("Key", new Iphone().getKey());
            conn.setUseCaches(false);
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(6000);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            //System.out.println("RS:" + rs);
        } catch (IOException var12) {
            new RuntimeException("请求服务器失败,请重新查询");
        } finally {
            conn.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * 将序列号查询结果存至文件
     * @param sn 序列号/IMEI
     * @param  str 查询返回的json字符串
     */

    private String saveResultToFile(String sn,String str)throws  IOException{
        System.out.println(str);
        JSONObject jsonObject= JSON.parseObject(str);
        System.out.println(jsonObject.getJSONObject("data"));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = simpleDateFormat.format(date);
        Md5Hash md5Hash = new Md5Hash(sn);
        String objectFilePrefix = md5Hash.toString();
        String objectSavePath = File.separator +"mydata"+File.separator + "queryInfo" + File.separator +"queryPhone"+File.separator + now + File.separator + objectFilePrefix + ".txt";
        saveObjectFile(objectSavePath,jsonObject.getJSONObject("data"));
        return  objectSavePath;
    }
    //将存有查询结果的文件URL存到数据库
    private void saveResultToMySql(QueryPhone queryPhone){
        try{
            queryDao.insertQueryPhone(queryPhone);
        }catch (Exception e){
            throw new RuntimeException("数据存入失败");
    }

    }
    public JSONObject readPhoneFile(String path){
        //根据path找到文件的位置
        File file = new File(path);
        JSONObject jsonObject;
        ObjectInputStream objectInputStream;
        try{
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            jsonObject = (JSONObject) objectInputStream.readObject();
            objectInputStream.close();
        }catch (IOException e){
            throw  new RuntimeException("文件未找到");
        }catch (Exception e1){
            throw new RuntimeException("读取文件失败");
        }
        return jsonObject;
    }
}
