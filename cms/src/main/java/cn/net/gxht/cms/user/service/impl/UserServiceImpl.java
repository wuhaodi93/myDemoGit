package cn.net.gxht.cms.user.service.impl;

import cn.net.gxht.cms.common.entity.MyMD5Util;
import cn.net.gxht.cms.common.entity.MyShiroMd5Util;
import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.common.entity.PageUtil;
import cn.net.gxht.cms.user.dao.UserDao;
import cn.net.gxht.cms.user.entity.AppUser;
import cn.net.gxht.cms.user.entity.ApplicationInfo;
import cn.net.gxht.cms.user.entity.Role;
import cn.net.gxht.cms.user.entity.User;
import cn.net.gxht.cms.user.service.UserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.net.gxht.cms.common.entity.PageUtil.byPage;

/**
 * Created by Administrator on 2017/10/9.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao dao;
    @Override
    public Map<String,Object> findUsers(Page page){
        User [] users= dao.findUserPromission();
        return byPage(users,page);
    }
    @Override
    public Map<String,Object> findUserAppliedInfo(Page page,Integer state) {
        System.out.println("page = [" + page + "], state = [" + state + "]");
        List<Map<String,Object>> merchants=dao.findUserAppliedInfo(state);
        List<Map<String,Object>> merchantsByPage=new ArrayList<Map<String, Object>>();
        System.out.println(merchants.size());
        page.setAmount(merchants.size());
        /**
         * 获得总数之后就可以获得总页数
         */
        page.setAllPage();
        /**
         * 获得起始条目
         */
        page.setStartIndex(page.getStartIndex());
        /**
         * 获得结束条目
         */
        page.setEndIndex(page.getEndIndex());
        System.out.println(page);
        int index=0;
        for(Map<String,Object> map:merchants){
            if(index>=page.getStartIndex()&&index<=page.getEndIndex()){
                merchantsByPage.add(map);
            }
            index++;
        }
        Map<String,Object> map=new HashedMap();
        map.put("pageObject",page);
        map.put("pageInfo",merchantsByPage);
        return map;
    }
    @Override
    public User findUserByName(String name) {
        return dao.findUserPromission(name);
    }
    @Override
    public Map<String, Object> findUnAuthUsers(Integer status,Page page,String keyword) {
        AppUser [] appUser=dao.AppFindUserAppliedInfo(status,keyword);
        System.out.println("appUsers:"+ArrayUtils.toString(appUser));
        page.setPageSize(5);
        Map<String,Object> map= byPage(appUser,page);
        return map;
    }
    @Override
    public void app_updateUserState(Integer state, Integer id,String failedMessage) {
        Integer tem=dao.app_updateUserAuthState(state, id,failedMessage);
        if(tem<=0){
            throw new RuntimeException("修改用户状态失败");
        }
    }
    @Override
    public Map<String, Object> app_findUserApplicationInfo(Page page,String keyword) {
        ApplicationInfo [] userApplicationArray =dao.app_findUserApplicationInfo(keyword);
        page.setPageSize(8);
        Map<String,Object> userApplicationMap= byPage(userApplicationArray,page);
        return userApplicationMap;
    }
    @Override
    public AppUser findUserInfo(Integer userId) {
        System.out.println("userId = [" + userId + "]");
        return dao.findUserInfo(userId);
    }
    @Override
    public Map<String, Object> findUserFeedBack(Page page) {
        List<Map<String,Object>> feedBackList=dao.findUserFeedBack();
        page.setPageSize(5);
        Map<String,Object> feedBackMap = PageUtil.byPage(feedBackList,page);
        return feedBackMap;
    }
    @Override
    public Role[] findRole() {
        return dao.findRole();
    }
    @Override
    public void InsertCmsUser(User user,Role role) throws UnsupportedEncodingException {
        String name=new String(user.getName().getBytes("ISO8859-1"),"utf-8");
        user.setName(name);
        user.setPassword(MyShiroMd5Util.getEncryptedPwd(user.getPassword()));
        Integer i=dao.insertCmsUser(user);
        if(i<=0){
            throw new RuntimeException("系统异常 0");
        }
        Integer userId=user.getUserId();
        Integer roleId=role.getRoleId();
        i=dao.authorization(userId,roleId);
        if(i<=0){
            throw new RuntimeException("系统异常 1");
        }
    }
    @Override
    public Map<String, Object> findAuthUserDataInMonth() {
        String lineName="实名数量";
        List<Map<String,Object>> list = dao.findAuthUserData();
        System.out.println(list);
        int size=list.size();
        Long [] times= new Long[size];
        String [] dates=new String[size];
        int i=0;
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String,Object> map:list){
            times[i]=(Long) map.get("times");
            dates[i]=sim.format((Date)map.get("date"));
            i++;
        }
        Map<String,Object> resultMap=new HashedMap();
        JSONObject jsonData=new JSONObject();
        JSONArray dataArray=new JSONArray();
        jsonData.put("name",lineName);
        jsonData.put("data",times);
        dataArray.add(jsonData);
        resultMap.put("categories",dates);
        resultMap.put("series",dataArray);
        return resultMap;
    }
    @Override
    public Map<String, Object> findUserRegisterData() {
        String lineName="注册数量";
        List<Map<String,Object>> list = dao.findUserRegisterData();
        System.out.println(list);
        int size=list.size();
        Long [] times= new Long[size];
        String [] dates=new String[size];
        int i=0;
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String,Object> map:list){
            times[i]=(Long) map.get("times");
            dates[i]=sim.format((Date)map.get("date"));
            i++;
        }
        Map<String,Object> resultMap=new HashedMap();
        JSONObject jsonData=new JSONObject();
        JSONArray dataArray=new JSONArray();
        jsonData.put("name",lineName);
        jsonData.put("data",times);
        dataArray.add(jsonData);
        resultMap.put("categories",dates);
        resultMap.put("series",dataArray);
        return resultMap;
    }
    @Override
    public void deleteCmsUser(Integer id) {
        Integer state = dao.deleteCmsUser(id);
        if(state<1){
            throw new RuntimeException("删除用户失败");
        }
    }
}
