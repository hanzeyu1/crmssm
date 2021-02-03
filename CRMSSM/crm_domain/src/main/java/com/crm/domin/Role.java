package com.crm.domin;

import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name Role
 * @date 2021/1/31 17:34
 */
public class Role {
    private String id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissions;
    private List<Users> usersList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
