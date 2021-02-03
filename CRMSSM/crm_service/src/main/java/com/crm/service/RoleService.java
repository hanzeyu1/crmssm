package com.crm.service;

import com.crm.domin.Permission;
import com.crm.domin.Role;

import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name RoleService
 * @date 2021/2/1 16:34
 */
public interface RoleService {
    List<Role> findAllRole() throws Exception;
    Role findRoleById(String id) throws Exception;
    List<Permission> findPermissionForAddRoleByRoleId(String id) throws Exception;
    void addPermissionsForRole(String rid,String[] ids) throws Exception;
    void addRole(Role role) throws Exception;
}
