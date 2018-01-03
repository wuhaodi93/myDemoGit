package cn.net.gxht.cms.user.controller;

import cn.net.gxht.cms.common.entity.JsonResult;
import cn.net.gxht.cms.common.entity.MyMD5Util;
import cn.net.gxht.cms.common.entity.MyShiroMd5Util;
import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.user.entity.GetTest;
import cn.net.gxht.cms.user.entity.Role;
import cn.net.gxht.cms.user.entity.User;
import cn.net.gxht.cms.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.jar.JarException;

/**
 * Created by Administrator on 2017/10/9.
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户登录(应该把业务写在service层)
     *
     * @param name     姓名
     * @param password 密码
     * @param session
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/doLogin")
    public String doFindUser(String name, String password, HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        name = new String(name.getBytes("ISO8859-1"), "UTF-8");
        Subject user = SecurityUtils.getSubject();
        System.out.println("name = [" + name + "], password = [" + password + "], session = [" + session + "]");
        password = MyShiroMd5Util.getEncryptedPwd(password);
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        token.setRememberMe(true);
        try {
            user.login(token);
        } catch (AuthenticationException ex) {
            session.setAttribute("loginErorr", "用户名或密码错误");
            return "/login";
        }
        session.removeAttribute("loginError");
        return "/main";
    }

    @RequestMapping("/getInfo")
    public String getInfo() {
        return "/main";
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.html";
    }

    /**
     * 查询所有后端用户
     *
     * @param page
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping("/findUsers")
    @ResponseBody
    public JsonResult findUsers(Page page) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return new JsonResult(userService.findUsers(page));
    }

    /**
     * 查找所有app认证信息
     *
     * @param page
     * @param state   用户认证的状态
     * @param keyword 可以根据关键字查询
     * @return
     */
    @RequestMapping("/findUnAuthUsers")
    @ResponseBody
    public JsonResult findUnAuthUsers(Page page, Integer state, String keyword) {
        return new JsonResult(userService.findUnAuthUsers(state, page, keyword));
    }

    /**
     * 更新app用户认证的状态
     *
     * @param state
     * @param id
     * @param failedMessage
     * @return
     */
    @RequestMapping("/AppUpdateUserState")
    @ResponseBody
    public JsonResult updateUserState(Integer state, Integer id, String failedMessage) {
        userService.app_updateUserState(state, id, failedMessage);
        return new JsonResult();
    }

    /**
     * 查找所有用户申请记录
     *
     * @param page
     * @param keyword 可以根据关键字查询
     * @return
     */
    @RequestMapping("/appFindUserApplication")
    @ResponseBody
    public JsonResult app_findUserApplicationInfo(Page page, String keyword) {
        System.out.println("page = [" + page + "], keyword = [" + keyword + "]");
        Map<String, Object> userApplicationMap = userService.app_findUserApplicationInfo(page, keyword);
        return new JsonResult(userApplicationMap);
    }

    /**
     * 根据id查找app用户的信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/findUserInfo")
    @ResponseBody
    public JsonResult findUserInfo(Integer id) {
        return new JsonResult(userService.findUserInfo(id));
    }

    /**
     * 获取app用户反馈的信息
     *
     * @param page
     * @return
     */
    @RequestMapping("/findUserFeedBack")
    @ResponseBody
    public JsonResult findUserFeedBack(Page page) {
        Map<String, Object> map = userService.findUserFeedBack(page);
        return new JsonResult(map);
    }

    /**
     * 获取所有的角色以便创建用户时选择
     *
     * @return
     */
    @RequestMapping("/findAllRole")
    @ResponseBody
    public JsonResult findAllRole() {
        Role[] roles = userService.findRole();
        return new JsonResult(roles);
    }

    /**
     * 创建后台用户
     *
     * @param user 用户
     * @param role 角色
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/insertCmsUser")
    @ResponseBody
    public JsonResult insertCmsUser(User user, Role role) throws UnsupportedEncodingException {
        userService.InsertCmsUser(user, role);
        return new JsonResult();
    }

    /**
     * 删除后台用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteCmsUser")
    @ResponseBody
    public JsonResult deleteCmsUser(Integer id) {
        userService.deleteCmsUser(id);
        return new JsonResult();
    }

//    @RequestMapping("/getOtherNetData")
//    @ResponseBody
//    public JSONObject getUserDownloadTimes(StringBuilder url) throws IOException {
//        JSONObject jsonObject = GetTest.sendGetReturnJsonString(null, url);
//        return jsonObject;
//    }

    /**
     * 获取一周之内用户认证的数量
     *
     * @return
     */
    @RequestMapping("/findAuthDataInMonth")
    @ResponseBody
    public JsonResult findAuthDataInMonth() {
        Map<String, Object> map = userService.findAuthUserDataInMonth();
        return new JsonResult(map);
    }

    /**
     * 获得一周之内用户注册的数量
     *
     * @return
     */
    @RequestMapping("/findUserRegisterDataInMonth")
    @ResponseBody
    public JsonResult findUserRegisterDataInMonth() {
        Map<String, Object> map = userService.findUserRegisterData();
        return new JsonResult(map);
    }
}