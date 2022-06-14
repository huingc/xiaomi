package com.huing.controller;

import com.huing.pojo.Collect;
import com.huing.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    CollectService collectService;

    @RequestMapping("/addCollect")
    public String addCollect(Collect collect, Model model,HttpSession session,int pid){
        Integer uid = (Integer) session.getAttribute("uid");
        System.out.println(pid);
        collect.setPid(pid);
        collect.setUid(uid);
        collectService.addCollect(collect);
        model.addAttribute("collectList",collectService.selectCollectBill());
        return "collect.jsp";
    }

    @RequestMapping("/allCollect")
    public String allCollect(Model model){
        model.addAttribute("collectList",collectService.selectCollectBill());
        System.out.println(collectService.selectCollectBill());
        return "collect.jsp";
    }

    @RequestMapping("/deleteCollect")
    public String deleteDept(int cid, Model model){
        collectService.deleteCollect(cid);
        System.out.println(cid);
        model.addAttribute("collectList",collectService.selectCollectBill());
        return "collect.jsp";
    }

}
