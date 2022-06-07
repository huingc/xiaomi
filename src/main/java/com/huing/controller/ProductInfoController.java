package com.huing.controller;

import com.github.pagehelper.PageInfo;
import com.huing.pojo.ProductInfo;
import com.huing.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 16:09
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoController {

    //每页记录数
    public static final int PAGE_SIZE = 5;

    @Autowired
    ProductInfoService productInfoService;

    //显示全部商品不分页
    @RequestMapping("/getAll")
    public String getAll(Model model) {
        List<ProductInfo> list = productInfoService.getAll();

        model.addAttribute("list", list);
        return "product";
    }

    //显示第一页的5条记录
    @RequestMapping("/split")
    public String split(Model model) {
        PageInfo pageInfo = productInfoService.splitPage(0, PAGE_SIZE);

        model.addAttribute("info", pageInfo);
        return "product";
    }


    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxsplit(int page, HttpSession session) {
        PageInfo pageInfo = productInfoService.splitPage(page, PAGE_SIZE);

        session.setAttribute("info", pageInfo);
        return;
    }
}
