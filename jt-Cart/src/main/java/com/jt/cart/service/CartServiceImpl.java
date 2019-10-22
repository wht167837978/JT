package com.jt.cart.service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.TbCart;
import com.jt.common.vo.SysResult;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    public List<TbCart> findCartByUserId(Long userId) {
        TbCart tbCart = new TbCart();
        tbCart.setUserId(userId);

        return cartMapper.select(tbCart);
    }

    public SysResult saveCart(TbCart cart) {


        try {
            TbCart cartDb = cartMapper.findByUseridItemid(cart);

            if (cartDb == null) {
                cart.setCreated(new Date());
                cart.setUpdated(cart.getCreated());
                cartMapper.insert(cart);
            } else {
                Long aa = cartDb.getNum() + cart.getNum();
                cartDb.setNum(aa);
                cartMapper.updateByPrimaryKeySelective(cartDb);
            }
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return SysResult.build(201, "加入购物车失败");
    }

    public void cartUpdate(TbCart tbCart) {
        tbCart.setUpdated(new Date());
        cartMapper.CartUpdate(tbCart);
    }

    public void delectCart(Long userId, Long itemId) {
        cartMapper.deleteCart(userId,itemId);

    }
}
