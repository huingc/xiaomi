package com.huing.service;

import com.github.pagehelper.PageInfo;
import com.huing.pojo.Bill;
import com.huing.pojo.BillInfo;
import com.huing.pojo.vo.BillVo;
import com.huing.pojo.vo.ProductInfoVo;

import java.util.List;

/**
 * kyf
 * 2022/6/9 22:22
 */
public interface BillService {
    List<Bill> selectlist();
    PageInfo splitPage(int pageNum, int pageSize);
    Bill getById(int bid);
    int deleteById(int bid);
    int deleteBatch(String[] ids);
    PageInfo splitPageVo(BillVo vo, int pageSize);
    List<BillInfo> selectByBid(int bid);
    int addBill(Bill bill);
    Bill selectgetbid(int uid);
    int addbillinfo(BillInfo billInfo);
    List<Bill> selectbillByuid(int uid);
}
