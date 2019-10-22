package com.jt.cart.service;

import com.jt.cart.pojo.TbCart;
import com.jt.common.vo.SysResult;

import java.util.List;

public interface CartService {
    List<TbCart> findCartByUserId(Long userId);

    SysResult saveCart(TbCart cart);

    void cartUpdate(TbCart tbCart);

    void delectCart(Long userId, Long itemId);
}
