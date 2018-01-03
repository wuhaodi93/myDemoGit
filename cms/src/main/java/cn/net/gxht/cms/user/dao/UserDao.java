package cn.net.gxht.cms.user.dao;

import cn.net.gxht.cms.user.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/9.
 */
public interface UserDao {
    User findUser(@Param(value = "name") String name);
    int insertUser(@Param(value="user") User user);
    User findUserPromission(@Param(value="name") String name);
    User [] findUserPromission();
    /**
     * 寻找所有通过认证的项目
     * @return
     */
    List<Map<String,Object>> findUserAppliedInfo(@Param(value="state") Integer state);
    AppUser [] AppFindUserAppliedInfo(@Param(value = "state") Integer state,@Param(value = "keyword") String keyword);
    Integer app_updateUserAuthState(@Param(value = "state") Integer state, @Param(value = "id") Integer id,@Param(value = "failedMessage") String failedMessage);

    ApplicationInfo [] app_findUserApplicationInfo(@Param(value = "keyword") String keyword);
    AppUser findUserInfo(@Param(value="id") Integer id);
    List<Map<String,Object>> findUserFeedBack();
    Role[] findRole();
    Integer insertCmsUser(User user);
    Integer authorization(@Param(value="userId") Integer userId,@Param(value="roleId") Integer roleId);
    List<Map<String,Object>> findAuthUserData();
    List<Map<String,Object>> findUserRegisterData();
    Integer deleteCmsUser(Integer id);
}
