package com.huing.service.impl;

import com.huing.mapper.ProductTypeMapper;
import com.huing.pojo.ProductType;
import com.huing.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 19:51
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {

        return productTypeMapper.selectAll();
    }
}
