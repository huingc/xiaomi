package com.huing.service;

import com.huing.pojo.Admin;

/**
 * @author huing
 * @create 2022-06-07 10:16
 */
public interface AdminService {
    //  完成登录判断
    Admin login(String name, String pwd);
}
