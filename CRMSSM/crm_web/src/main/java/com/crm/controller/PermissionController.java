package com.crm.controller;

import com.crm.domin.Permission;
import com.crm.result.PageBean;
import com.crm.service.PermissionService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name PermissionController
 * @date 2021/2/1 17:48
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") int nowPage,
                                @RequestParam(defaultValue = "5") int pageSize){
        PageBean pageBean = null;
        try {
            pageBean = permissionService.findAll(nowPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("pageBean",pageBean);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEST')")
    public String save(Permission permission){
        try {
            permissionService.addPermission(permission);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/permission/findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        Permission permission = null;
        try {
            permission = permissionService.findPermissionById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/deletePermission")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEST')")
    public String deletePermission(String id){
        try {
            permissionService.delPermission(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/permission/findAll.do";
    }
}
