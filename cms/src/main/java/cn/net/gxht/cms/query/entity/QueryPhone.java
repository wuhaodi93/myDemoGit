package cn.net.gxht.cms.query.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/29.
 */
public class QueryPhone {
    /**
     * 序列号/IMEI码
     */
    private String sn;
    /**
     *查询人
     */
    private String queryBy;
    /**
     *文件路径
     */
    private String infoFilePath;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getQueryBy() {
        return queryBy;
    }

    public void setQueryBy(String queryBy) {
        this.queryBy = queryBy;
    }

    public String getInfoFilePath() {
        return infoFilePath;
    }

    public void setInfoFilePath(String infoFilePath) {
        this.infoFilePath = infoFilePath;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryPhone{");
        sb.append("sn='").append(sn).append('\'');
        sb.append(", queryBy='").append(queryBy).append('\'');
        sb.append(", infoFilePath='").append(infoFilePath).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
