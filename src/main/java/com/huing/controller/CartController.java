package com.huing.controller;

import com.huing.pojo.Bill;
import com.huing.pojo.BillInfo;
import com.huing.pojo.Cart;
import com.huing.pojo.ProductInfo;
import com.huing.service.BillService;
import com.huing.service.CartService;
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
 * 2022/6/11 14:36
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BillService billService;
    @Autowired
    private ProductInfoService productInfoService;
//    去个人购物车页面
    @RequestMapping("/tocart")
    public String tocart(HttpSession session, Model model, HttpServletRequest request){
        if(session.getAttribute("uid")==null){
            request.setAttribute("msg","没有登入请先登入");
            return "userlogin.jsp";
        }
        System.out.println(cartService.selectByUid((Integer) session.getAttribute("uid")));
        model.addAttribute("info",cartService.selectByUid((Integer) session.getAttribute("uid")));
        return "cart.jsp";
    }
//    添加到购物车
    @RequestMapping("/addcart")
    public String addcart(int pid,int amount,HttpSession session, HttpServletRequest request){
        if(session.getAttribute("uid")==null){
            request.setAttribute("msg","没有登入请先登入");
            return "userlogin.jsp";
        }
        Cart cart = new Cart();
        cart.setNum(amount);
        cart.setP_id(pid);
        cart.setUid((Integer) session.getAttribute("uid"));
        Cart cart1 = cartService.selectByPid(cart);
        if(cart1!=null){
            cart.setNum(amount+cart1.getNum());
            cartService.update(cart);
            System.out.println(cartService.update(cart));
        }else{
            cartService.addCart(cart);
            System.out.println(cartService.addCart(cart));
        }
        return "cart.jsp";
    }
//    购物车信息删除
    @RequestMapping("/delcart")
    public String delcart(int pid,HttpSession session){
        Cart cart= new Cart();
        cart.setUid((Integer) session.getAttribute("uid"));
        cart.setP_id(pid);
        cartService.delCart(cart);
        return "forward:/cart/tocart.action";
    }
//    数量增加
    @RequestMapping("/numadd")
    public String addnum(int num,int pid,HttpSession session){
        Cart cart= new Cart();
        num+=1;
        cart.setNum(num);
        cart.setP_id(pid);
        cart.setUid((Integer) session.getAttribute("uid"));
        cartService.update(cart);
        return "forward:/cart/tocart.action";
    }
//    数量减少
    @RequestMapping("/numreduce")
    public String reducenum(int num,int pid,HttpSession session){
        Cart cart= new Cart();
        num-=1;
        cart.setNum(num);
        cart.setP_id(pid);
        cart.setUid((Integer) session.getAttribute("uid"));
        cartService.update(cart);
        return "forward:/cart/tocart.action";
    }
//    账单结算
    @RequestMapping("/settlement")
    public String settlement(HttpServletRequest request,HttpSession session){
        String[] pids = request.getParameterValues("pid");
        if(pids==null){
            return "forward:/cart/tocart.action";
        }
        int uid= (int) session.getAttribute("uid");
        double total=0;
        int count=0;
        for (String pid : pids) {
            Cart cart= new Cart();
            cart.setUid(uid);
            int i = Integer.parseInt(pid);
            cart.setP_id(i);
            Cart cart1 = cartService.selectBypidanduid(cart);
            total+=cart1.getNum()*cart1.getProductInfo().getpPrice();
            count+=cart1.getNum();
        }
        Bill bill = new Bill();
        bill.setUid(uid);
        bill.setCount(count);
        bill.setDate(new Date());
        bill.setTotal(total);
        billService.addBill(bill);
//        添加新的账单
        Bill bill1 = billService.selectgetbid(uid);
        for (String pid : pids) {
            Cart cart= new Cart();
            cart.setUid(uid);
            int i = Integer.parseInt(pid);
            cart.setP_id(i);
            Cart cart1 = cartService.selectBypidanduid(cart);
//            给新的账单添加详细信息
            BillInfo billInfo = new BillInfo();
            billInfo.setBid(bill1.getBid());
            billInfo.setP_id(i);
            billInfo.setNum(cart1.getNum());
            billService.addbillinfo(billInfo);
//            删除购物车信息
            cartService.delCart(cart);
//            数据库商品数量减少
            ProductInfo byId = productInfoService.getById(i);
            byId.setpNumber(byId.getpNumber()-cart1.getNum());
            productInfoService.update(byId);
        }

        return "forward:/cart/tocart.action";
    }
}
