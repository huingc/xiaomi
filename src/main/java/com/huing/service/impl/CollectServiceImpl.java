package com.huing.service.impl;

import com.huing.mapper.CollectMapper;
import com.huing.pojo.Collect;
import com.huing.pojo.ProductInfo;
import com.huing.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public void addCollect(Collect collect) {

        collectMapper.addCollect(collect);
    }

    @Override
    public List<Collect> selectCollectBill() {
        return collectMapper.selectCollectBill();
    }

    @Override
    public int deleteCollect(int cid) {
        return collectMapper.deleteCollect(cid);
    }

//    @Override
//    public List<Collect> selectCollectBillName() {
//        return collectMapper.selectCollectBillName();
//    }
}
