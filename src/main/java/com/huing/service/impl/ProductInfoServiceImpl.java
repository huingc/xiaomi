package com.huing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huing.mapper.ProductInfoMapper;
import com.huing.pojo.ProductInfo;
import com.huing.pojo.vo.ProductInfoVo;
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

    @Override
    public ProductInfo getById(int id) {

        return productInfoMapper.selectById(id);
    }

    @Override
    public int update(ProductInfo productInfo) {
        return productInfoMapper.updateById(productInfo);
    }

    @Override
    public int deleteById(int pid) {
        return productInfoMapper.deleteById(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductInfoVo vo) {
        return productInfoMapper.selectCondition(vo);
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductInfoVo vo, int pageSize) {
        //分页插件使用PageHelper工具完成分页设置
        if (vo.getPage() == null){
            vo.setPage(1);
        }
        PageHelper.startPage(vo.getPage(),pageSize);

        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
        return new PageInfo<>(list);
    }
}
