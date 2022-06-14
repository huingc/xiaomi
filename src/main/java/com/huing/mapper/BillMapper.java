package com.huing.mapper;

import com.huing.pojo.Bill;
import com.huing.pojo.BillInfo;
import com.huing.pojo.vo.BillVo;
import java.util.List;

/**
 * kyf
 * 2022/6/9 22:11
 */
public interface BillMapper {
//    账单列表
    List<Bill> selectlist();
//    账单分页列表
    List<Bill> selectAllByPage();
//    id查询账单
    Bill getById(int bid);
//    id删除账单
    int deleteById(int bid);
//    批量删除账单
    int deleteBatch(String[] ids);
//    分页跳转
    List<Bill> selectCondition(BillVo vo);
//    账单详情
    List<BillInfo> selectByBid(int bid);
//    添加账单
    int addBill(Bill bill);
//    通过用户id查询账单
    Bill selectgetbid(int uid);
//    账单详情填充
    int addbillinfo(BillInfo billInfo);
//    查询当前用户账单全部信息
    List<Bill> selectbillByuid(int uid);
}