package cn.net.gxht.cms.file.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/11.
 */
public class App {
    private Integer id;
    //文件名
    private String name;
    //文件备注
    private String remark;
    //文件版本
    private String version;
    //文件上传时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String uploadUser;

    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    //文件下载次数
    private Integer downloadTimes;
    //由MD5为文件生成的摘要
    private String digest;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "App{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", version='" + version + '\'' +
                ", createTime=" + createTime +
                ", uploadUser='" + uploadUser + '\'' +
                ", downloadTimes=" + downloadTimes +
                ", digest='" + digest + '\'' +
                '}';
    }
}
