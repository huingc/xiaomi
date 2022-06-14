package com.huing.service;

import com.huing.pojo.Cart;

import java.util.List;

/**
 * kyf
 * 2022/6/11 15:01
 */
public interface CartService {
    List<Cart> selectByUid(int uid);
    Cart selectByPid(Cart cart);
    int addCart(Cart cart);
    int update(Cart cart);
    int delCart(Cart cart);
    Cart selectBypidanduid(Cart cart);
}
