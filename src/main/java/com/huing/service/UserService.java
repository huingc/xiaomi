package com.huing.service;

import com.huing.pojo.Reset;
import com.huing.pojo.User;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-09 8:50
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param user 用户的数据对象
     */
    int reg(User user);

    /**
     * 用户登录功能
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数据, 没有返回null
     */
    User login(String username, String password);

    List<User> findListUser();

    List<User> queryList(String username);

    User findUsersById(int uid);

    void updateUser(User user);

    int updatePwd(User user);

    List<Reset> findListReset();

    void resetUser(Reset reset);

    int deleteUser(int rid);

    void updateReset(User user);

    void deleteReset(String username);

    User findReset(String username);
}
