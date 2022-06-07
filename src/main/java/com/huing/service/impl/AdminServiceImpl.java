package com.huing.service.impl;

import com.huing.mapper.AdminMapper;
import com.huing.pojo.Admin;
import com.huing.service.AdminService;
import com.huing.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 10:17
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * @param name
     * @param pwd
     * @return
     */
    @Override
    public Admin login(String name, String pwd) {

        //根据传入的用户名到DB中查对象
        //如果有条件，则一定要创建AdminExample的对象，用来封装条件
        Admin admin = adminMapper.findByName(name);
        if (admin != null){
            //两个密码进行对比（密码是密文）
            String MD5_pwd = MD5Util.getMD5(pwd);
            if (MD5_pwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
