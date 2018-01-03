package cn.net.gxht.cms.user.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */
public class Role {
    private Integer roleId;
    private String role;
    private String roleDescription;
    private List<Promission> promissions;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Promission> getPromissions() {
        return promissions;
    }

    public void setPromissions(List<Promission> promissions) {
        this.promissions = promissions;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", promissions=" + promissions +
                '}';
    }
}
