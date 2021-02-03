package com.crm.dao;

import com.crm.domin.Permission;
import com.crm.domin.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name RoleMapper
 * @date 2021/2/1 16:32
 */
public interface RoleMapper {
    List<Role> findAllRole() throws Exception;
    Role findRoleById(String id) throws Exception;
    List<Permission> findPermissionForAddRoleByRoleId(String id) throws Exception;
    void addPermissionsForRole(@Param("pid") String pid, @Param("rid") String rid) throws Exception;
    void addRole(Role role) throws Exception;
}
