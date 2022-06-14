package com.huing.mapper;


import com.huing.pojo.Reset;
import com.huing.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户模块的持久层接口
 *
 * @author qzg
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

    @Select("select * from t_user")
    List<User> findListUser();


    @Select("select * from t_user where username like CONCAT('%',#{username},'%') ")
    List<User> queryList(String username);

    @Select("select * from t_user where uid=#{uid}")
    User findUsersById(int uid);

    void updateUser(User user);

    void updatePwd(User user);

    @Select("select * from reset")
    List<Reset> findListReset();

    void resetUser(Reset reset);

    @Delete("delete from reset where rid=#{rid}")
    int deleteUser(int rid);

    void updateReset(User user);

    @Delete("delete from reset where username=#{username}")
    int deleteReset(String username);


    @Select("select * from reset where username=#{username}")
    User findReset(String username);

}
