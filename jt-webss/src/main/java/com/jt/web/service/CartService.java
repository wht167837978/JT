package com.jt.web.service;



import com.jt.web.pojo.TbCart;

import java.util.List;

public interface CartService {
    List<TbCart> findCartByUserId(Long id);

    void SaveCart(TbCart cart);

    void cartUpdate(TbCart tbCart);

    void deleteCart(TbCart tbCart);
}
