package cn.net.gxht.cms.user.entity;

/**
 * Created by Administrator on 2017/10/16.
 */
public class AppAuthImg {
    private Integer imgId;
    private String path;
    private Integer typeId;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
