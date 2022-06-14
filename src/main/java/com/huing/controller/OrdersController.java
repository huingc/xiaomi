package com.huing.controller;

import com.huing.pojo.Bill;
import com.huing.pojo.BillInfo;
import com.huing.pojo.ProductInfo;
import com.huing.service.BillService;
import com.huing.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * kyf
 * 2022/6/11 21:39
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private BillService billService;
    @Autowired
    private ProductInfoService productInfoService;
//    去个人账单页面
    @RequestMapping("/toorders")
    public String orders(HttpSession session, Model model, HttpServletRequest request){
        if(session.getAttribute("uid")==null){
            request.setAttribute("msg","没有登入请先登入");
            return "userlogin.jsp";
        }
        int uid = (int)session.getAttribute("uid");
        model.addAttribute("info",billService.selectbillByuid(uid));
        return "orders.jsp";
    }
    //    立即购买添加账单
    @RequestMapping("/addbillandbillinfo")
    public String addbill(int pid,int amount,HttpSession session,HttpServletRequest request){
        if(session.getAttribute("uid")==null){
            request.setAttribute("msg","没有登入请先登入");
            return "userlogin.jsp";
        }
//        添加新账单
        Bill bill = new Bill();
        bill.setUid((int)session.getAttribute("uid"));
        bill.setCount(amount);
        bill.setDate(new Date());
        bill.setTotal((double) (productInfoService.getById(pid).getpPrice()*amount));
        billService.addBill(bill);
//        添加新账单信息
        BillInfo billInfo = new BillInfo();
        billInfo.setNum(amount);
        billInfo.setP_id(pid);
        billInfo.setBid(billService.selectgetbid((int)session.getAttribute("uid")).getBid());
        billService.addbillinfo(billInfo);
//        商品库存减少
        ProductInfo byId = productInfoService.getById(pid);
        byId.setpNumber(byId.getpNumber()-amount);
        productInfoService.update(byId);

        return "forward:/orders/toorders.action";
    }

}
