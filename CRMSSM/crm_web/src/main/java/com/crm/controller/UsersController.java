package com.crm.controller;

import com.crm.domin.Users;
import com.crm.result.PageBean;
import com.crm.service.UsersService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name UsersController
 * @date 2021/2/1 11:05
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(defaultValue = "1")int nowPage,
                                @RequestParam(defaultValue = "2")int pageSize){
        PageBean<Users> pageBean = null;
        try {
            pageBean = usersService.findAllUsers(nowPage, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("pageBean",pageBean);
        mv.setViewName("users-list");
        return mv;
    }

    @RequestMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEST')")
    public String saveUsers(Users users){
        try {
            usersService.saveUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/users/findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        Users users = null;
        try {
            users = usersService.findUsersById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("user",users);
        mv.setViewName("users-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        Users users = usersService.findRolesForAddRolesToUsers(id);

        ModelAndView mv = new ModelAndView();
        mv.addObject("users",users);
        mv.setViewName("users-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEST')")
    public String addRoleToUser(String userId,String[] ids){
        try {
            usersService.addRoleToUser(userId,ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/users/findById.do?id="+userId;
    }
}
