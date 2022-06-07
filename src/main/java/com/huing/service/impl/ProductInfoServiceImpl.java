package com.huing.service.impl;

import com.huing.mapper.ProductInfoMapper;
import com.huing.pojo.ProductInfo;
import com.huing.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 15:59
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {

        return productInfoMapper.selectAll();
    }
}
