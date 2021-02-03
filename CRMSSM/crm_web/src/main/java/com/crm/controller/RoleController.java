package com.crm.controller;

import com.crm.domin.Permission;
import com.crm.domin.Role;
import com.crm.service.RoleService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name RoleController
 * @date 2021/2/1 16:37
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Role> roles = null;
        try {
            roles = roleService.findAllRole();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("roles",roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        Role role = null;
        try {
            role = roleService.findRoleById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        List<Permission> permissions = null;
        try {
            permissions = roleService.findPermissionForAddRoleByRoleId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("permissions",permissions);
        mv.addObject("roleId",id);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEST')")
    public String addPermissionToRole(String roleId,String[] ids){
        try {
            roleService.addPermissionsForRole(roleId, ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEST')")
    public String save(Role role){
        try {
            roleService.addRole(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/role/findAll.do";
    }
}
