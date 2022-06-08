package com.huing.test;

import com.huing.mapper.ProductInfoMapper;
import com.huing.pojo.ProductInfo;
import com.huing.pojo.vo.ProductInfoVo;
import com.huing.utils.MD5Util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 9:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class Mytest {
    @Test
    public void testMD5(){
        String mi = MD5Util.getMD5("000000");
        System.out.println(mi);
    }

//    @Autowired
//    ProductInfoMapper productInfoMapper;
//    @Test
//    public void testselectCondition(){
//        ProductInfoVo vo = new ProductInfoVo();
//        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
//        System.out.println(list);
//    }
}
