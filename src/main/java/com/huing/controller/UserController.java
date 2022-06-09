package com.huing.controller;

import com.huing.pojo.User;
import com.huing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author huing
 * @create 2022-06-09 8:40
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/admin")
    public String admin(){
        return "login.jsp";
    }

    @RequestMapping("/toregist")
    public String toregist(){
        return "regist.jsp";
    }

    @RequestMapping("/regist")
    public String regist(User user, Model model){
        int result = userService.reg(user);
        if (result == -1){
            model.addAttribute("msg","用户名已被占用");
            return "regist.jsp";
        }else if (result == 0){
            model.addAttribute("msg","注册出现未知异常，请联系管理员");
            return "regist.jsp";
        }else if (result == 1){
            model.addAttribute("msg","注册成功");
            return "userlogin.jsp";
        }else {
            return "err.jsp";
        }
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session,Model model){
        User data = userService.login(username, password);

        if (data == null){
            model.addAttribute("msg","用户密码错误");
            return "userlogin.jsp";
        }else if (data.getIsDelete() == 1){
            model.addAttribute("msg","用户数据不存在");
            return "userlogin.jsp";
        }else {
            //  向session对象中完成数据的绑定（session全局的）
            session.setAttribute("uid", data.getUid());
            session.setAttribute("username", data.getUsername());
            return "success.jsp";
        }
    }
}
