package com.jt.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.cart.pojo.TbCart;
import com.jt.cart.service.CartService;
import com.jt.cart.service.CartServiceImpl;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    private static ObjectMapper objectMapper=new ObjectMapper();

    //	http://cart.jt.com/cart/query/{userId}
    @RequestMapping("/query/{userId}")
    @ResponseBody
    public SysResult findCartByUserId(@PathVariable("userId") Long userId) {
        try {
            List<TbCart> cartList = cartService.findCartByUserId(userId);
            return SysResult.oK(cartList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "查询失败");
    }

    @RequestMapping("/save")
    @ResponseBody
    public SysResult cartSave(TbCart cart) {
        try {
            return cartService.saveCart(cart);
        }catch (Exception e){
            e.printStackTrace();
        }
return SysResult.build(201,"加入购物车失败");
    }
//
//    @RequestMapping("/delete")
//    @ResponseBody
//    public SysResult cartDelete(HttpServletRequest request,Long itemId) {
//        try {
//            Cookie[] cookies = request.getCookies();
//            for (Cookie cookie:cookies){
//                if(cookie.equals("JT_TICKET")){
//
//                }
//            }
//            return cartService.DeleyeCart(cart);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return SysResult.build(201,"删除失败");
//    }
    @RequestMapping("/update/num")
    @ResponseBody
    public SysResult cartUpdate(String cartJson){
        try {
            TbCart tbCart = objectMapper.readValue(cartJson, TbCart.class);
            cartService.cartUpdate(tbCart);
            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
       return SysResult.build(201,"查询失败");

    }
    @RequestMapping("/delete/{userId}/{itemId}")
    @ResponseBody
public SysResult deleteCart(@PathVariable Long userId,@PathVariable Long itemId){
        try {
            cartService.delectCart(userId,itemId);
            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
 return SysResult.build(201,"删除失败") ;
}
}
