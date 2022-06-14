package com.huing.service.impl;

import com.huing.mapper.CartMapper;
import com.huing.pojo.Cart;
import com.huing.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * kyf
 * 2022/6/11 15:02
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public List<Cart> selectByUid(int uid) {
        return cartMapper.selectByUid(uid);
    }

    @Override
    public Cart selectByPid(Cart cart) {
        return cartMapper.selectByPid(cart);
    }

    @Override
    public int addCart(Cart cart) {
        return cartMapper.addCart(cart);
    }

    @Override
    public int update(Cart cart) {
        return cartMapper.update(cart);
    }

    @Override
    public int delCart(Cart cart) {
        return cartMapper.delCart(cart);
    }

    @Override
    public Cart selectBypidanduid(Cart cart) {
        return cartMapper.selectBypidanduid(cart);
    }
}
