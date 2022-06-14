package com.huing.mapper;


import com.huing.pojo.Bill;
import com.huing.pojo.Collect;
import com.huing.pojo.ProductInfo;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface CollectMapper {

    void addCollect(Collect collect);

    List<Collect> selectCollectBill();

    @Delete("delete from collect where cid=#{cid}")
    int deleteCollect(int cid);

//    List<Collect> selectCollectBillName();

}
