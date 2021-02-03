package com.crm.service;

import com.crm.dao.UsersMapper;
import com.crm.domin.Users;
import com.crm.result.PageBean;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name UserService
 * @date 2021/1/31 18:04
 */
public interface UsersService extends UserDetailsService {

    PageBean<Users> findAllUsers(int nowPage,int pageSize) throws Exception;
    void saveUsers(Users users) throws Exception;
    Users findUsersById(String id) throws Exception;
    Users findRolesForAddRolesToUsers(String id);
    void addRoleToUser(String userId,String[] ids) throws  Exception;

}
