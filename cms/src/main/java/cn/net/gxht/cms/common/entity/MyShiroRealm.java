package cn.net.gxht.cms.common.entity;

import cn.net.gxht.cms.user.entity.Promission;
import cn.net.gxht.cms.user.entity.Role;
import cn.net.gxht.cms.user.entity.User;
import cn.net.gxht.cms.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2017/10/9.
 *
 */
class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    //设置一个ReamlName
    /**
     * 用于授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String currentUserName=(String) super.getAvailablePrincipal(principalCollection);
        List<String> roleList=new ArrayList<String>();
        System.out.println(currentUserName);
        List<String> promissionList=new ArrayList<String>();
        if(currentUserName==null){
            return null;
        }
        User user=userService.findUserByName(currentUserName);
        if(user!=null){
            if(null!=user.getRoles()&&user.getRoles().size()>0){
                for(Role role:user.getRoles()){
                    roleList.add(role.getRole());
                    for(Promission promission:role.getPromissions()){
                        promissionList.add(promission.getPromission());
                    }
                    System.out.println(promissionList);
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        simpleAuthorizationInfo.addStringPermissions(promissionList);
        return simpleAuthorizationInfo;
    }

    @Override
    public void setName(String name) {
        super.setName("myShiroRealm");
    }

    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /**
         * subject调用Realm的时候会把用户的账号和密码等信息封装到token里面
         */
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user=null;
        /**
         * 根据用户名获取user对象
         */
        user=userService.findUserByName(token.getUsername());
        if(user!=null){
            AuthenticationInfo authenticationInfo= null;
                authenticationInfo = new SimpleAuthenticationInfo(user.getName(),user.getPassword(),user.getName());
            /**
             * 将当前用户放到shiro的session里面
             */
                setSession("currentUser",user);
            return  authenticationInfo;
        }else {
            throw new AuthenticationException();
        }
    }

    private void setSession(Object key,Object value){
        Subject subject= SecurityUtils.getSubject();
        if(subject!=null){
            Session session=subject.getSession();
            /**
             * 设置session用不过时,其实设置1天之内不过时即可
             */
            session.setTimeout(-1000L);
            if(session!=null){
                session.setAttribute(key,value);
            }
        }
    }
}
