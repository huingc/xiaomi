package com.huing.controller;

import com.huing.pojo.Inform;
import com.huing.pojo.Reset;
import com.huing.pojo.User;
import com.huing.service.InformService;
import com.huing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author qzg
 * @create 2022-06-09 8:40
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private InformService informService;

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

    @RequestMapping("/toindex")
    public String toindex(){
        return "index.jsp";
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
            Inform inform = informService.newInform();
            model.addAttribute("inform",inform);
            return "index.jsp";
        }
    }

    @RequestMapping("/getAllUser")
    public String getAllUser(Model model){
        model.addAttribute("userList",userService.findListUser());
        return "user.jsp";
    }

    @RequestMapping("/queryList")
    public String queryList(String username, HttpServletRequest request){
        request.setAttribute("userList",userService.queryList(username));
        return "user.jsp";
    }
    @RequestMapping("/resetUser")
    public String queryResetUserList(String username, Model model){
        model.addAttribute("userList",userService.queryList(username));
        return "user.jsp";
    }

    @RequestMapping("/queryUserById")
    public String queryUserById(int uid, Model model,HttpSession session){
        model.addAttribute("user",userService.findUsersById(uid));
        User data =userService.findUsersById(uid);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        return "userDetail.jsp";
    }

    @RequestMapping("/queryUserById1")
    public String queryUserById1(int uid, Model model,HttpSession session){
        model.addAttribute("user",userService.findUsersById(uid));
        User data =userService.findUsersById(uid);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        return "updatePwd.jsp";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user,Model model,HttpSession session){
        userService.updateUser(user);
        User data = userService.findUsersById(user.getUid());
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        return "index.jsp";
    }


    @RequestMapping ("/updatePwd")
    public String updatePwd(User user,Model model,HttpSession session){
        userService.updatePwd(user);
        User data = userService.findUsersById(user.getUid());
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        return "index.jsp";
    }


    @RequestMapping("/getAllReset")
    public String getAllReset(Model model){
        model.addAttribute("resetList",userService.findListReset());
        return "reset.jsp";
    }
    @RequestMapping("/deleteReset")
    public String deleteReset(int rid, Model model){
        model.addAttribute("reset",userService.deleteUser(rid));
        model.addAttribute("resetList",userService.findListReset());
        return "reset.jsp";
    }
    @RequestMapping("/toReset")
    public String toReset(){
        return "addReset.jsp";
    }

    @RequestMapping("/resetPwd")
    public String resetPwd(Reset reset, Model model){
        if (userService.findReset(reset.getUsername())!=null){
            return "two.jsp";
        }else {
            userService.resetUser(reset);
            return "one.jsp";
        }
    }

    @RequestMapping("/updateReset")
    public String updateReset(User user,Model model){
        userService.updateReset(user);
        userService.deleteReset(user.getUsername());
        model.addAttribute("resetList",userService.findListReset());
        return "reset.jsp";
    }

}
