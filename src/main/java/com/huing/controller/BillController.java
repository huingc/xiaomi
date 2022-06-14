package com.huing.controller;

import com.github.pagehelper.PageInfo;
import com.huing.pojo.Bill;
import com.huing.pojo.BillInfo;
import com.huing.pojo.vo.BillVo;
import com.huing.pojo.vo.ProductInfoVo;
import com.huing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * kyf
 * 2022/6/9 21:53
 */
@Controller
@RequestMapping("bill")
public class BillController {

    //每页记录数
    public static final int PAGE_SIZE = 5;
    @Autowired
    private BillService billService;
    @RequestMapping("billlist")
    public String billlist(HttpSession session, Model model){

//        分页查询
        PageInfo pageInfo = null;

//        Object vo = session.getAttribute("prodvo");
        Object vo_delete = session.getAttribute("deleteProdVo");
//        if (vo != null) {
//            pageInfo = billService.splitPageVo((BillVo) vo, PAGE_SIZE);
//            session.removeAttribute("prodvo");
//        }
        if (vo_delete != null){
            pageInfo = billService.splitPageVo((BillVo) vo_delete, PAGE_SIZE);
            session.removeAttribute("deleteProdVo");
        } else {
            pageInfo = billService.splitPage(0, PAGE_SIZE);
        }

        model.addAttribute("info", pageInfo);
//        model.addAttribute("info", billService.selectlist());
        return "bill.jsp";
    }
//    查询
    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxsplit(BillVo vo, HttpSession session) {
        System.err.println(vo);
        PageInfo pageInfo = billService.splitPageVo(vo, PAGE_SIZE);
        session.setAttribute("info", pageInfo);
        return;
    }
//    查询一个账单,然后更改
//    @RequestMapping("/one")
//    public String one(int bid, BillVo vo, Model model, HttpSession session) {
//        Bill info = billService.getById(bid);
//        model.addAttribute("prod", info);
//
//        //将多条件及页码放入session中，更新结束后读取条件和页码
//        session.setAttribute("prodvo", vo);
//
//        return "update.jsp";
//    }



//      删除一个账单
    @RequestMapping("/delete")
    public String delete(int bid, Model model, HttpSession session, BillVo vo) {
        int result = -1;
        try {
            System.err.println(bid);
            result = billService.deleteById(bid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
            model.addAttribute("msg", "删除成功");
            //删除成功后，需要携带当前页码和多条件
            session.setAttribute("deleteProdVo",vo);
        } else {
            model.addAttribute("msg", "该订单不可删除");
        }
        return "forward:/bill/billlist.action";
    }
//    后台账单批量删
    @RequestMapping("/deletebatch")
    public String deleteBatch(String pids, Model model) {
        String[] ps = pids.split(",");
        try {
            int result = billService.deleteBatch(ps);
            if (result > 0) {
                model.addAttribute("msg", "批量删除成功");
            } else {
                model.addAttribute("msg", "批量删除失败");
            }
        } catch (Exception e) {
            model.addAttribute("msg", "该订单不可删除");
        }
        return "forward:/bill/billlist.action";
    }
//    跳到账单明细页面
    @RequestMapping("/billinfo")
    public String billinfo(int bid,Model model){
        model.addAttribute("info",billService.selectByBid(bid));
        model.addAttribute("infobid",billService.selectByBid(bid).get(0).getBid());
        return "billinfo.jsp";
    }

}
