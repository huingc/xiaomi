package com.huing.controller;

import com.huing.pojo.Admin;
import com.huing.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Service;

/**
 * @author huing
 * @create 2022-06-07 10:38
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    //实现登陆判断，并进行相应跳转
    @RequestMapping("/login")
    public String login(String name, String pwd, Model model) {

        Admin admin = adminService.login(name, pwd);

        if (admin != null) {
            //登陆成功
            model.addAttribute("admin",admin);
            return "main";
        } else {
            //登陆失败
            model.addAttribute("errmsg","用户名或密码不正确!");
            return "login";
        }

    }
}
