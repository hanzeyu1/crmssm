package com.crm.service.impl;

import com.crm.dao.UsersMapper;
import com.crm.domin.Role;
import com.crm.domin.Users;
import com.crm.result.PageBean;
import com.crm.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @author hanzeyu
 * @version 1.0
 * @name UserServiceImpl
 * @date 2021/1/31 18:06
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * userDetailService中提供的认证的规范
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //自己定义的与数据库信息对应的用户信息
        Users users = null;
        try {
             users = usersMapper.findUsersForRoleByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //spring security提供的用于框架执行认证的user对象
        User user = new User(users.getUsername(),users.getPassword(),
                users.getStatus() == 0 ? false : true,true,true,
                true,getAuthorities(users.getRoles()));

        return user;
    }

    /**
     * 将获取到的角色信息封装成spring security需要的SimpleGrantedAuthority
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    /**
     * 查询所有用户信息
     * @param nowPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public PageBean<Users> findAllUsers(int nowPage, int pageSize) throws Exception {
        PageHelper.startPage(nowPage,pageSize);
        List<Users> usersList = usersMapper.findAllUsers();
        PageInfo<Users> pageInfo = new PageInfo<Users>(usersList);

        PageBean<Users> pageBean = new PageBean<Users>();
        pageBean.setNowPage(nowPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount((int)pageInfo.getTotal());
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setList(usersList);
        pageBean.cal();
        return pageBean;
    }

    /**
     * 添加用户
     * @param users
     * @throws Exception
     */
    public void saveUsers(Users users) throws Exception {
        //密码加密
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        usersMapper.addUsers(users);
    }

    /**
     * 通过id查询users
     * @param id
     * @return
     * @throws Exception
     */
    public Users findUsersById(String id) throws Exception {
        Users users = usersMapper.findUsersForPermissionById(id);
        return users;
    }

    public Users findRolesForAddRolesToUsers(String id) {
        Users user = null;
        try {
            user = usersMapper.findUsersById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user != null) {
            List<Role> roles = null;
            try {
                roles = usersMapper.findRolesForAddRolesToUsers(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setRoles(roles);
        }
        return user;
    }

    public void addRoleToUser(String userId, String[] ids) throws Exception{
        for (String id : ids) {
            usersMapper.addRoleForUser(userId,id);
        }
    }
}
