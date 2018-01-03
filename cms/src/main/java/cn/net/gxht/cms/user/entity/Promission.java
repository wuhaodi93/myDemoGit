package cn.net.gxht.cms.user.entity;

/**
 * Created by Administrator on 2017/10/12.
 */
public class Promission {
    private Integer promissionId;
    private String promission;

    public Integer getPromissionId() {
        return promissionId;
    }

    public void setPromissionId(Integer promissionId) {
        this.promissionId = promissionId;
    }

    public String getPromission() {
        return promission;
    }

    public void setPromission(String promission) {
        this.promission = promission;
    }

    @Override
    public String toString() {
        return "Promission{" +
                "promissionId=" + promissionId +
                ", promission='" + promission + '\'' +
                '}';
    }
}
