package com.huing.controller;

import com.huing.pojo.ProductInfo;
import com.huing.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 16:09
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoController {

    @Autowired
    ProductInfoService productInfoService;

    //显示全部商品不分页
    @RequestMapping("/getAll")
    public String getAll(Model model){
        List<ProductInfo> list = productInfoService.getAll();

        model.addAttribute("list",list);
        return "product";
    }
}
