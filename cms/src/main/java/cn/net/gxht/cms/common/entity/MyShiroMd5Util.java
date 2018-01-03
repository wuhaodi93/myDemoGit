package cn.net.gxht.cms.common.entity;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by Administrator on 2017/10/12.
 */
public class MyShiroMd5Util {
    public static final String SALT="ABCDEFGXYJKLMNOPQRSTUVWXYZ0123456789";
    public static final int TIMES=100;

    /**
     * 判断当前密码和数据库中的密码是否相等
     * 数据库中的密码是经过getEncryptedPwd加密过的
     * 只要判断用户输入的密码经过getEncryptedPwd加密后是否与数据库中的密码是否想等即可
     *
     * @param password
     * @param passwordInDb
     * @return
     */
    public static boolean validPassword(String password, String passwordInDb){
        password=getEncryptedPwd(password);
        if(password.equals(passwordInDb)){
            return true;
        }else {
            return false;
        }
    }
    public static String getEncryptedPwd(String password){
        Md5Hash md5Hash=new Md5Hash(password,SALT,TIMES);
        return md5Hash.toString();
    }
}
