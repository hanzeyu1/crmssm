package com.crm.service.impl;

import com.crm.dao.PermissionMapper;
import com.crm.domin.Permission;
import com.crm.result.PageBean;
import com.crm.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name PermissionServiceImpl
 * @date 2021/2/1 17:47
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    public PageBean<Permission> findAll(int nowPage, int pageSize) throws Exception{
        PageHelper.startPage(nowPage, pageSize);
        List<Permission> permissions = permissionMapper.findAll();
        PageInfo pageInfo = new PageInfo(permissions);

        PageBean<Permission> pageBean = new PageBean<Permission>();
        pageBean.setNowPage(nowPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount((int)pageInfo.getTotal());
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setList(permissions);
        pageBean.cal();
        return pageBean;
    }

    public void addPermission(Permission permission) throws Exception {
        permissionMapper.addPermission(permission);
    }

    public Permission findPermissionById(String id) throws Exception {
        Permission permission = permissionMapper.findPermissionById(id);
        return permission;
    }

    public void delPermission(String id) throws Exception {
        permissionMapper.delRoleForDelPermissionByPermissionId(id);
        permissionMapper.delPermissionById(id);
    }
}
