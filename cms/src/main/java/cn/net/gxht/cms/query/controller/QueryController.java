package cn.net.gxht.cms.query.controller;

import cn.net.gxht.cms.common.entity.JsonResult;
import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.query.entity.QueryPhone;
import cn.net.gxht.cms.query.entity.QueryUserInfo;
import cn.net.gxht.cms.query.service.QueryService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/18.
 */
@Controller
@Transactional(propagation = Propagation.REQUIRED)
public class QueryController {
    @Resource
    private QueryService queryService;

    /**
     * 查询对象的信息，将对象的查询记录的基本信息存到数据库,把查询的结果以对象输出流输出到文件并保存,
     * 后面要查找之前查询的记录时可以直接从文件中取出json对象,避免了创建多个表,写一大堆sql语句等操作
     *
     * @param queryUserInfo
     * @return
     * @throws IOException
     */
    @RequestMapping("/queryObjectInfo")
    @ResponseBody
    public JsonResult queryUserInfo(QueryUserInfo queryUserInfo) throws IOException {
        //处理每个第三方请求参数名不同
        System.out.println("queryUserInfo = [" + queryUserInfo.toString() + "]");
        queryUserInfo.setName(new String(queryUserInfo.getName().getBytes("iso8859-1"), "utf-8"));
        queryUserInfo.setRealName(queryUserInfo.getName());
        queryUserInfo.setId(queryUserInfo.getCardNo());
        queryUserInfo.setIdNo(queryUserInfo.getCardNo());
        queryUserInfo.setIdCard(queryUserInfo.getCardNo());
        queryUserInfo.setQuery_mobile(queryUserInfo.getPhone());
        JSONObject jsonObject = queryService.getQueryInfo(queryUserInfo);
        return new JsonResult(jsonObject);
    }

    /**
     * 寻找所有的之前查询记录并分页
     *
     * @return
     */
    @RequestMapping("/findQueryRecord")
    @ResponseBody
    public JsonResult findQueryRecord(Page page, String keyword) {
        System.out.println("page = [" + page + "], keyword = [" + ArrayUtils.toString(keyword) + "]");
        Map<String, Object> map = queryService.findQueryRecord(page, keyword);
        return new JsonResult(map);
    }

    /**
     * 读取文件获得json对象(查询信息)
     *
     * @param id
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @RequestMapping("/readObjectFileById")
    @ResponseBody
    public JsonResult readObjectFileById(Integer id) throws IOException, ClassNotFoundException {
        System.out.println("id = [" + id + "]");
        JSONObject jsonObject = queryService.readObjectFile(id);
        return new JsonResult(jsonObject);
    }

    /**
     * 点击查询按钮，跳转到查询页面，页面加载完发送请求，然后再展现数据
     * state=1表示之前有查询过，现在知识从到当前文件中获取json对象
     * state=0表示要从接口中查询数据
     */
    @RequestMapping("/skipToQueryInfoJsp")
    public String skipToQueryInfoJsp(Integer id, String name, String phone, String idCard, String qq, String email, HttpSession session, Integer state) throws UnsupportedEncodingException {
        System.out.println("id = [" + id + "], name = [" + name + "], phone = [" + phone + "], idCard = [" + idCard + "], qq = [" + qq + "], email = [" + email + "], session = [" + session + "], state = [" + state + "]");
        if (state == 1) {
            session.setAttribute("id", id);
            session.setAttribute("state", 1);
        } else {
            name = new String(name.getBytes("iso8859-1"), "utf-8");
            session.setAttribute("name", name);
            session.setAttribute("phone", phone);
            session.setAttribute("idCard", idCard);
            session.setAttribute("qq", qq);
            session.setAttribute("email", email);
            session.setAttribute("id", null);
            session.setAttribute("state", 0);
        }
        return "/query/queryinfo";
    }

    /**
     * 实现调用接口查询苹果序列号/IEMI返回对应信息的JSON字符串
     * 并进行相应的储存处理
     */
    @RequestMapping("/doGetSericaAndIEMIInfo")
    @ResponseBody
    public JsonResult doGetSericaAndIEMIInfo(String sericaAndIEMI){
        //System.out.println("序列号:"+sericaAndIEMI+"种类:"+type);
        String str= queryService.getSericaAndIEMIInfo(sericaAndIEMI);
     //  String str="{\"code\":0,\"data\":{\"imei\":\"\",\"sn\":\"F2LV689YJCLP\",\"model\":\"iPhone 8 Plus\",\"storage\":\"256GB\",\"color\":\"Gold\",\"status\":\"normal\",\"activated\":\"\",\"purchase\":{\"date\":\"2017-09-30\",\"validated\":\"\",\"estimated\":\"2017-09-30\",\"country\":\"\"},\"coverage\":\"2018-09-29\",\"daysleft\":276,\"support\":\"2017-12-29\",\"applecare\":false,\"repair\":\"none\",\"sales\":\"DS01\",\"fmi\":\"\",\"icloud\":\"\",\"loaner\":\"N\",\"manufacture\":{\"date\":\"2017-08-05\",\"factory\":\"中国（郑州）\"},\"img\":\"http://acs.3023.com/apple/zrup.png\"}}";
        return new JsonResult(str);
    }
    /**
     * 通过序列号/IMEI查询历史记录
     */
    @RequestMapping("/doReadObjectFileBySn")
    @ResponseBody
    public JsonResult doReadObjectFileBySn(Page page, String sn){
        System.out.println("sn:"+sn);
        Map<String, Object> map=queryService.findQueryPhone(page,sn);
        return new JsonResult(map);
    }
    /**
     * 读取文件获得json对象(查询信息)
     *
     * @param path
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @RequestMapping("/readPhoneFileByUrl")
    @ResponseBody
    public JsonResult readPhoneFileByUrl(String path) throws IOException, ClassNotFoundException {
        //System.out.println("path = [" + path + "]");
        JSONObject jsonObject = queryService.readPhoneFile(path);
        return new JsonResult(jsonObject);
    }
}
