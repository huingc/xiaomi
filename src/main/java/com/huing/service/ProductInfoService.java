package com.huing.service;

import com.github.pagehelper.PageInfo;
import com.huing.pojo.ProductInfo;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-15 18:00
 */
public interface ProductInfoService {
    List<ProductInfo> getAll();

    //分页
    PageInfo splitPage(int pageNum,int pageSize);

    int save(ProductInfo info);
}
