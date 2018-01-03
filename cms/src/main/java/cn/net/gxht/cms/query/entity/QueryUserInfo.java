package cn.net.gxht.cms.query.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/17.
 */
public class QueryUserInfo{
    private Integer queryUserInfoId;
    //查询人姓名
    private String name;
    private String realName;
    //查询人身份证
    private String cardNo;
    private String id;
    private String idNo;
    private String idCard;
    //查询类型
    private StringBuilder queryType;
    //查询人手机号
    private String phone;
    private String query_mobile;
    //查询人email
    private String query_email;
    //查询人qq
    private String query_qq;
    private String InfoFilePath;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String queryBy;
    private String appkey="916ee64c0f3fe063fbe0ab28cc21f948";
    public String getQueryBy() {
        return queryBy;
    }

    public void setQueryBy(String queryBy) {
        this.queryBy = queryBy;
    }

    public Integer getQueryUserInfoId() {
        return queryUserInfoId;
    }

    public void setQueryUserInfoId(Integer queryUserInfoId) {
        this.queryUserInfoId = queryUserInfoId;
    }

    public String getInfoFilePath() {
        return InfoFilePath;
    }

    public void setInfoFilePath(String infoFilePath) {
        InfoFilePath = infoFilePath;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public StringBuilder getQueryType() {
        return queryType;
    }

    public void setQueryType(StringBuilder queryType) {
        this.queryType = queryType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuery_mobile() {
        return query_mobile;
    }

    public void setQuery_mobile(String query_mobile) {
        this.query_mobile = query_mobile;
    }

    public String getQuery_email() {
        return query_email;
    }

    public void setQuery_email(String query_email) {
        this.query_email = query_email;
    }

    public String getQuery_qq() {
        return query_qq;
    }

    public void setQuery_qq(String query_qq) {
        this.query_qq = query_qq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "QueryUserInfo{" +
                "queryUserInfoId=" + queryUserInfoId +
                ", appkey='" + appkey + '\'' +
                ", name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", id='" + id + '\'' +
                ", idNo='" + idNo + '\'' +
                ", idCard='" + idCard + '\'' +
                ", queryType=" + queryType +
                ", phone='" + phone + '\'' +
                ", query_mobile='" + query_mobile + '\'' +
                ", query_email='" + query_email + '\'' +
                ", query_qq='" + query_qq + '\'' +
                ", InfoFilePath='" + InfoFilePath + '\'' +
                ", createTime=" + createTime +
                ", queryBy='" + queryBy + '\'' +
                '}';
    }
}
