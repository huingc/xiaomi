package com.huing.service;

import com.huing.pojo.Collect;
import com.huing.pojo.ProductInfo;

import java.util.List;

public interface CollectService {

    void addCollect(Collect collect);

    List<Collect> selectCollectBill();

    int deleteCollect(int cid);

//    List<Collect> selectCollectBillName();
}
