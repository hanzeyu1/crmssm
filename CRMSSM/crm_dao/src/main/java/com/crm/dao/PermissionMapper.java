package com.crm.dao;

import com.crm.domin.Permission;

import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name PermissionMapper
 * @date 2021/2/1 17:43
 */
public interface PermissionMapper {
    List<Permission> findAll() throws Exception;
    void addPermission(Permission permission) throws Exception;
    Permission findPermissionById(String id) throws Exception;
    void delRoleForDelPermissionByPermissionId(String id) throws Exception;
    void delPermissionById(String id) throws Exception;
}
