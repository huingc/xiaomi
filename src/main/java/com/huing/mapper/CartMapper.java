package com.huing.mapper;

import com.huing.pojo.Cart;

import java.util.List;

/**
 * kyf
 * 2022/6/11 14:46
 */
public interface CartMapper {
//    购物车列表
    List<Cart> selectByUid(int uid);
//    查询购物车内是否有此商品
    Cart selectByPid(Cart cart);
//    商品添加
    int addCart(Cart cart);
//    商品数量更改
    int update(Cart cart);
//    购物车删除商品
    int delCart(Cart cart);
//    结算时获取商品数量和商品id
    Cart selectBypidanduid(Cart cart);
}
