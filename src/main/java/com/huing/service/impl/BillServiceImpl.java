package com.huing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huing.mapper.BillMapper;
import com.huing.pojo.Bill;
import com.huing.pojo.BillInfo;
import com.huing.pojo.ProductInfo;
import com.huing.pojo.vo.BillVo;
import com.huing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * kyf
 * 2022/6/9 22:23
 */
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;
    @Override
    public List<Bill> selectlist() {
        return billMapper.selectlist();
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具完成分页设置
        PageHelper.startPage(pageNum,pageSize);

        //运行PageInfo数据封装
        //将拿到的集合封装进PageInfo对象中
        List<Bill> list = billMapper.selectAllByPage();
        PageInfo<Bill> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public Bill getById(int bid) {
        return billMapper.getById(bid);
    }

    @Override
    public int deleteById(int bid) {
        return billMapper.deleteById(bid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return billMapper.deleteBatch(ids);
    }

    @Override
    public PageInfo splitPageVo(BillVo vo, int pageSize) {
        //分页插件使用PageHelper工具完成分页设置
        if (vo.getPage() == null){
            vo.setPage(1);
        }
        PageHelper.startPage(vo.getPage(),pageSize);

        List<Bill> list = billMapper.selectCondition(vo);
        return new PageInfo<>(list);
    }

    @Override
    public List<BillInfo> selectByBid(int bid) {
        return billMapper.selectByBid(bid);
    }

    @Override
    public int addBill(Bill bill) {
        return billMapper.addBill(bill);
    }

    @Override
    public Bill selectgetbid(int uid) {
        return billMapper.selectgetbid(uid);
    }

    @Override
    public int addbillinfo(BillInfo billInfo) {
        return billMapper.addbillinfo(billInfo);
    }

    @Override
    public List<Bill> selectbillByuid(int uid) {
        return billMapper.selectbillByuid(uid);
    }
}
