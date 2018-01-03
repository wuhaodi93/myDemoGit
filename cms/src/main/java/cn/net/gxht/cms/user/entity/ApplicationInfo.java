package cn.net.gxht.cms.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/2.
 */
public class ApplicationInfo {
    private Integer applyInfoId;
    private String applicationUserName;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String applicationUserPhone;
    private Integer applicationUserId;

    public Integer getApplyInfoId() {
        return applyInfoId;
    }

    public void setApplyInfoId(Integer applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

    public String getApplicationUserName() {
        return applicationUserName;
    }

    public void setApplicationUserName(String applicationUserName) {
        this.applicationUserName = applicationUserName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getApplicationUserPhone() {
        return applicationUserPhone;
    }

    public void setApplicationUserPhone(String applicationUserPhone) {
        this.applicationUserPhone = applicationUserPhone;
    }

    public Integer getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(Integer applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    @Override
    public String toString() {
        return "ApplicationInfo{" +
                "applyInfoId=" + applyInfoId +
                ", applicationUserName='" + applicationUserName + '\'' +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", applicationUserPhone='" + applicationUserPhone + '\'' +
                ", applicationUserId=" + applicationUserId +
                '}';
    }
}
