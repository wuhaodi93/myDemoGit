package cn.net.gxht.cms.user.service;

import cn.net.gxht.cms.common.entity.Page;
import cn.net.gxht.cms.user.entity.AppUser;
import cn.net.gxht.cms.user.entity.Role;
import cn.net.gxht.cms.user.entity.User;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/9.
 */
public interface UserService {
    Map<String,Object> findUsers(Page page) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    Map<String,Object> findUserAppliedInfo(Page page,Integer state);
    User findUserByName(String name);
    Map<String,Object> findUnAuthUsers(Integer status,Page page,String keyword);
    void app_updateUserState(Integer state,Integer id,String failedMessage);
    Map<String,Object> app_findUserApplicationInfo(Page page,String keyword);
    AppUser findUserInfo(Integer userId);
    Map<String,Object> findUserFeedBack(Page page);
    Role [] findRole();
    void InsertCmsUser(User user,Role role) throws UnsupportedEncodingException;
    Map<String,Object> findAuthUserDataInMonth();
    Map<String,Object> findUserRegisterData();
    void deleteCmsUser(Integer id);
}
