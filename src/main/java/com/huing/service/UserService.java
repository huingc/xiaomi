package com.huing.service;

import com.huing.pojo.User;

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
}
