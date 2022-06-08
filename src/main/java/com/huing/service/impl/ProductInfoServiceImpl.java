package com.huing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {

        //分页插件使用PageHelper工具完成分页设置
        PageHelper.startPage(pageNum,pageSize);

        //运行PageInfo数据封装
        //将拿到的集合封装进PageInfo对象中
        List<ProductInfo> list = productInfoMapper.selectAllByPage();
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }
}
