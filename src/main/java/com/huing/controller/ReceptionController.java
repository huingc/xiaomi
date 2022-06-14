package com.huing.controller;

import com.huing.pojo.ProductInfo;
import com.huing.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-09 14:16
 */
@Controller
@RequestMapping("/products")
public class ReceptionController {

    @Autowired
    ProductInfoService productInfoService;

    @RequestMapping("/hot_list")
    @ResponseBody
    public List<ProductInfo> hot_list(){
        List<ProductInfo> hotList = productInfoService.findHotList();
        return hotList;
    }

    @RequestMapping("/new_list")
    @ResponseBody
    public List<ProductInfo> new_list(){
        List<ProductInfo> newList = productInfoService.findNewList();
        return newList;
    }
//    返回商品详情页
    @RequestMapping("/productinfo")
    public String toproductinfo(int id, Model model){
        ProductInfo byId = productInfoService.getById(id);
        model.addAttribute("info",byId);
        return "productinfo.jsp";
    }

}
