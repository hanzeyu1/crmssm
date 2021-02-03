package com.crm.dao;

import com.crm.domin.Role;
import com.crm.domin.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name UsersMapper
 * @date 2021/1/31 17:41
 */
public interface UsersMapper {
    Users findUsersForRoleByUsername(String username) throws  Exception;
    Users findUsersForPermissionById(String id) throws Exception;
    List<Users> findAllUsers() throws  Exception;
    void addUsers(Users users) throws Exception;
    void addRoleForUser(@Param("uid") String uid, @Param("rid") String rid) throws Exception;
    List<Role> findRolesForAddRolesToUsers(String uid) throws Exception;
    Users findUsersById(String id) throws Exception;

}
