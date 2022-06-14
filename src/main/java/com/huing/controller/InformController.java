package com.huing.controller;



import com.huing.pojo.Inform;
import com.huing.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author tzy
 * @create 2022-06-13 15:27
 */
@Controller
@RequestMapping("/info")
public class InformController {
    @Autowired
    InformService informService;

    @RequestMapping("/addInform")
    public String addInform(Inform inform, Model model){
        informService.addInform(inform);
        model.addAttribute("informList",informService.selectAll());
        return "inform.jsp";
    }


    @RequestMapping("/addInformGo")
    public String addInformGo(){
        return "addInform.jsp";
    }

    @RequestMapping("/allInform")
    public String getAllInform(Model model){
        model.addAttribute("informList",informService.selectAll());
        return "inform.jsp";
    }

    @RequestMapping("/allInform2")
    public String getAllInform2(Model model){
        model.addAttribute("informList",informService.selectAll());
        return "informDetail.jsp";
    }

    @RequestMapping("/deleteInform")
    public String deleteInform(int iId, Model model){
        informService.deleteInform(iId);
//        model.addAttribute("informList",informService.selectAll());
        return "redirect:allInform.action";
    }

    @RequestMapping("/updateInformGo")
    public String updateInformGo(int iId, Model model){
        System.err.println("222");
        Inform inform = informService.getInformId(iId);
        model.addAttribute("inform",inform);
        return "updateInform.jsp";
    }

    @RequestMapping("/updateInform")
    public String updateInform(Inform inform, Model model){
        System.err.println("111111111111");
        informService.updateInform(inform);
        model.addAttribute("informList",informService.selectAll());
        return "inform.jsp";
    }

    @RequestMapping("/selectInform")
    public String selectInform(int iId, Model model){
        informService.selectInform(iId);
        model.addAttribute("informList",informService.selectAll());
        return "inform.jsp";
    }

    @RequestMapping("/informList")
    public String informList(String iName, HttpServletRequest request){
        request.setAttribute("informList",informService.informList(iName));
        return "inform.jsp";
    }


}
