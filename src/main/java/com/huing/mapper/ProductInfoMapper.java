package com.huing.mapper;

import com.huing.pojo.ProductInfo;
import com.huing.pojo.vo.ProductInfoVo;

import java.util.List;

/**
 * @author huing
 * @create 2022-06-07 16:01
 */
public interface ProductInfoMapper {

    List<ProductInfo> selectAll();

    /**
     * 查询所有（使用分页插件）
     * @return
     */
    List<ProductInfo> selectAllByPage();

    /**
     * 插入商品
     * @param info
     * @return
     */
    int insert(ProductInfo info);

    /**
     * 根据id查
     * @param id
     * @return
     */
    ProductInfo selectById(int id);

    /**
     * 更新商品
     * @param productInfo
     * @return
     */
    int updateById(ProductInfo productInfo);

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
     * 查询热销商品的前四名
     *
     * @return 热销商品前四名的集合
     */
    List<ProductInfo> findHotList();

    /**
     * 查询新品商品的前四名
     *
     * @return 新品商品前四名的集合
     */
    List<ProductInfo> findNewList();
}
