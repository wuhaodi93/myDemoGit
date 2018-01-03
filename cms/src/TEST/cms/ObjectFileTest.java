package cms;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.query.entity.QueryUserInfo;
import com.alibaba.fastjson.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/28.
 */
public class ObjectFileTest {
    @Test
    public void fastJsonTest()throws Exception{
        String str="{\"code\":0,\"data\":{\"imei\":\"\",\"sn\":\"F2LV689YJCLP\",\"model\":\"iPhone 8 Plus\",\"storage\":\"256GB\",\"color\":\"Gold\",\"status\":\"normal\",\"activated\":\"\",\"purchase\":{\"date\":\"2017-09-30\",\"validated\":\"\",\"estimated\":\"2017-09-30\",\"country\":\"\"},\"coverage\":\"2018-09-29\",\"daysleft\":276,\"support\":\"2017-12-29\",\"applecare\":false,\"repair\":\"none\",\"sales\":\"DS01\",\"fmi\":\"\",\"icloud\":\"\",\"loaner\":\"N\",\"manufacture\":{\"date\":\"2017-08-05\",\"factory\":\"中国（郑州）\"},\"img\":\"http://acs.3023.com/apple/zrup.png\"}}";
        JSONObject jsonObject=JSON.parseObject(str);
        System.out.println(jsonObject.getJSONObject("data"));
        Date date = new Date();
        String sn="F2LV689YJCLP";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = simpleDateFormat.format(date);
        Md5Hash md5Hash = new Md5Hash(sn);
        String objectFilePrefix = md5Hash.toString();
        String objectSavePath = "D:" + File.separator + "queryInfo" + File.separator + now + File.separator + objectFilePrefix + ".txt";
        saveObjectFile(objectSavePath,jsonObject.getJSONObject("data"));


    }
    @Test
    public void readFileTest()throws Exception{
        Md5Hash md5Hash = new Md5Hash("F2LV689YJCLP");
        String objectFilePrefix = md5Hash.toString();
        String path="D:" + File.separator + "queryInfo" + File.separator + "20171229" + File.separator + objectFilePrefix + ".txt";
        JSONObject jsonObject1=readObjectFile(path);
        System.out.println(jsonObject1);
    }
    private void saveObjectFile(String path, JSONObject jsonObject) throws IOException {
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
    private JSONObject readObjectFile(String path) throws IOException, ClassNotFoundException {
        //根据path找到文件的位置
        File file = new File(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        JSONObject jsonObject = (JSONObject) objectInputStream.readObject();
        objectInputStream.close();
        return jsonObject;
    }
}
