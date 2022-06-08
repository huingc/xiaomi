package com.huing.mapper;

import com.huing.pojo.ProductInfo;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 16:01
 */
public interface ProductInfoMapper {

    List<ProductInfo> selectAll();

    List<ProductInfo> selectAllByPage();

    int insert(ProductInfo info);
}
