package com.huing.service;

import com.github.pagehelper.PageInfo;
import com.huing.pojo.ProductInfo;
import com.huing.pojo.vo.ProductInfoVo;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-09-15 18:00
 */
public interface ProductInfoService {
    List<ProductInfo> getAll();

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo splitPage(int pageNum,int pageSize);

    /**
     * 增加商品
     * @param info
     * @return
     */
    int save(ProductInfo info);

    /**
     * 按主键id查询商品
     * @param id
     * @return
     */
    ProductInfo getById(int id);

    /**
     * 更新商品
     * @param productInfo
     * @return
     */
    int update(ProductInfo productInfo);

    /**
     * 删除商品
     * @param pid
     * @return
     */
    int deleteById(int pid);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(String[] ids);

    /**
     * 多条件查询
     * @param vo
     * @return
     */
    List<ProductInfo> selectCondition(ProductInfoVo vo);

    /**
     * 多条件查询(分页)
     * @param vo
     * @return
     */
    PageInfo splitPageVo(ProductInfoVo vo,int pageSize);

    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<ProductInfo> findHotList();

    /**
     * 查询新品商品的前四名
     * @return 新品商品前四名的集合
     */
    List<ProductInfo> findNewList();
}
