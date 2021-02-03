package com.crm.service.impl;

import com.crm.dao.RoleMapper;
import com.crm.domin.Permission;
import com.crm.domin.Role;
import com.crm.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name RoleServiceImpl
 * @date 2021/2/1 16:35
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    public List<Role> findAllRole() throws Exception {
        List<Role> roles = roleMapper.findAllRole();
        return roles;
    }

    public Role findRoleById(String id) throws Exception {
        Role role = roleMapper.findRoleById(id);
        return role;
    }

    public List<Permission> findPermissionForAddRoleByRoleId(String id) throws Exception {
        List<Permission> permissions = roleMapper.findPermissionForAddRoleByRoleId(id);
        return permissions;
    }

    public void addPermissionsForRole(String rid, String[] ids) throws Exception {
        for (String id : ids) {
            roleMapper.addPermissionsForRole(id,rid);
        }
    }

    public void addRole(Role role) throws Exception {
        roleMapper.addRole(role);
    }
}
