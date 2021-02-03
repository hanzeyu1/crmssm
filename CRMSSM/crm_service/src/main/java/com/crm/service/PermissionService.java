package com.crm.service;

import com.crm.domin.Permission;
import com.crm.result.PageBean;

import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name PermissionService
 * @date 2021/2/1 17:46
 */
public interface PermissionService {
    PageBean<Permission> findAll(int nowPage, int pageSize) throws  Exception;
    void addPermission(Permission permission) throws Exception;
    Permission findPermissionById(String id) throws Exception;
    void delPermission(String id) throws Exception;
}
