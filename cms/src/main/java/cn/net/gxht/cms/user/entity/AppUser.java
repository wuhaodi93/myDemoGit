package cn.net.gxht.cms.user.entity;

;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */
public class AppUser {
    private Integer id;
    private String account;
    private String phone;
    private String city;
    private String authName;
    private String authCardNo;
    private String img;
    private String authStatuName;

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", authName='" + authName + '\'' +
                ", authCardNo='" + authCardNo + '\'' +
                ", img='" + img + '\'' +
                ", authStatuName='" + authStatuName + '\'' +
                ", authTime=" + authTime +
                ", authImgs=" + authImgs +
                '}';
    }

    public String getAuthStatuName() {
        return authStatuName;
    }

    public void setAuthStatuName(String authStatuName) {
        this.authStatuName = authStatuName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date authTime;
    private List<AppAuthImg> authImgs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthCardNo() {
        return authCardNo;
    }

    public void setAuthCardNo(String authCardNo) {
        this.authCardNo = authCardNo;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public List<AppAuthImg> getAuthImgs() {
        return authImgs;
    }

    public void setAuthImgs(List<AppAuthImg> authImgs) {
        this.authImgs = authImgs;
    }

}
