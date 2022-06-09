package com.huing.service.impl;

import com.huing.mapper.UserMapper;
import com.huing.pojo.User;
import com.huing.service.UserService;
import com.huing.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author huing
 * @create 2022-06-09 8:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int reg(User user) {
        //通过user参数获取传递过来的username
        String username = user.getUsername();
        //调用findByUsername(username)判断用户是否被注册
        User result = userMapper.findByUsername(username);
        if (result != null){
            return -1;   //被占用
        }

        //密码加密处理:md5算法的形式
        //(串 + password + 串) ------md5算法进行加密,连续加载三次
        //盐值 + password + 盐值 -------盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        //获取盐值(随机生成一个盐值)
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值作为一个整体进行加密处理
        String md5Password = getMD5Password(oldPassword, salt);
        //将加密后的密码补全设置到user对象中
        user.setPassword(md5Password);
        user.setSalt(salt);

        //补全数据：is_delete设置为0
        user.setIsDelete(0);
        //补全数据：4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务功能的实现(rows==1)
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            return 0;
        }
        return 1;
    }

    @Override
    public User login(String username, String password) {
        //根据用户名查用户的数据，如果不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if (result == null) {
            return null;
        }

        //检测用户密码是否匹配
        //1,先获取数据库中加密之后的密码
        String oldPassword = result.getPassword();

        //2.和用户的传递过来的密码进行比较
        //2.1 先获取盐值：上一次在注册时自动生成的盐值
        String sal = result.getSalt();
        //2.2 将用户的密码按照相同的md5算法的规则进行加密
        String newMd5Password = getMD5Password(password, sal);

        //3.将密码进行比较
        if (!newMd5Password.equals(oldPassword)) {
            //  throw new PasswordNotMatchException("用户密码错误");
            return null;
        }


        //调用mapper层的findByUsername来查询用户的数据,提升系统性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        //判断is_delete字段的值是否为1表示被标记为删除
        if (result.getIsDelete() == 1) {
            user.setIsDelete(1);
        }else {
            user.setIsDelete(0);
        }
        //将当前的用户数据返回,返回的数据是为了辅助其他页面做数据展示使用（uid,username，avatar）
        return user;
    }

    /**
     * 定义一个md5算法的加密处理
     */
    private String getMD5Password(String password, String salt) {
        //md5加密算法的调用(进行3次加密)
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        //返回加密之后的密码
        return password;
    }
}
