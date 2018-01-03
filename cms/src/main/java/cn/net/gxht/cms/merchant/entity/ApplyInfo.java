package cn.net.gxht.cms.merchant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * Created by Administrator on 2017/10/18.
 */

/**
 * appPicture.title applyType
 * FROM (SELECT
 * city.cityName
 */
public class ApplyInfo {
    private Integer id;
    //申请入驻的企业营业执照
    private String businessLicense;
    //申请 标题
    private String title;
    //申请内容
    private String content;
    //申请日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    //失败原因
    private String failedMessage;
    //商户类型
    private String merchantType;
    //商户所申请的城市
    private String cityName;
    //申请人姓名
    private String userName;
    //申请人手机号
    private String phone;
    //申请商标
    private String img;
    //申请类型
    private String applyType;
    //申请的公司名称
    private String companyName;
    //审核状态
    private Integer status;
    //审核状态名称
    private String statuName;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatuName() {
        return statuName;
    }

    public void setStatuName(String statuName) {
        this.statuName = statuName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFailedMessage() {
        return failedMessage;
    }

    public void setFailedMessage(String failedMessage) {
        this.failedMessage = failedMessage;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ApplyInfo{" +
                "id=" + id +
                ", businessLicense='" + businessLicense + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", failedMessage='" + failedMessage + '\'' +
                ", merchantType='" + merchantType + '\'' +
                ", cityName='" + cityName + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", img='" + img + '\'' +
                ", applyType='" + applyType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", status=" + status +
                ", statusName='" + statuName + '\'' +
                '}';
    }
}
