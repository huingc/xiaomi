package com.huing.mapper;


import com.huing.pojo.User;

/**
 * 用户模块的持久层接口
 *
 * @author hxk
 * @create 2022-05-27 15:02
 */
public interface UserMapper {
    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     *
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    User findByUsername(String username);
}
