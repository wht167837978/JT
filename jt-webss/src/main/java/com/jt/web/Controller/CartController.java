package com.jt.web.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jt.web.ThreadLocal.UserThreadLocal;
import com.jt.web.pojo.TbCart;
import com.jt.web.pojo.UUser;
import com.jt.web.service.CartService;
import com.jt.web.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private CartService cartService;

    //跳转到购物车
    @RequestMapping("/show")
    public String Show(HttpServletRequest request, Model model) {
        String json = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("JT_TICKET".equals(cookie.getName())) {
                String token = cookie.getValue();
                Set<String> sentinels = new HashSet<String>();
                sentinels.add("172.16.48.110:26379");
                JedisSentinelPool pool =
                        new JedisSentinelPool("mymaster", sentinels);
                Jedis jedis = pool.getResource();
                json = jedis.get("" + token);
                try {
                    UUser uUser = objectMapper.readValue(json, UUser.class);
                    List<TbCart> cartList = cartService.findCartByUserId(uUser.getId());
                    model.addAttribute("cartList", cartList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return "cart";
    }

    @RequestMapping("/add/{itemId}")
    public String saveCart(@PathVariable("itemId") Long itemId, TbCart cart, HttpServletRequest request) {
        String json = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("JT_TICKET".equals(cookie.getName())) {
                String token = cookie.getValue();
                Set<String> sentinels = new HashSet<String>();
                sentinels.add("172.16.48.110:26379");
                JedisSentinelPool pool =
                        new JedisSentinelPool("mymaster", sentinels);
                Jedis jedis = pool.getResource();
                json = jedis.get("" + token);
                try {
                    UUser uUser = objectMapper.readValue(json, UUser.class);
                    cart.setItemId(itemId);
                    cart.setUserId(uUser.getId());
                    cartService.SaveCart(cart);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/cart/show.html";
    }

    /**
     * 更新购物车
     */
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public SysResult CartUpdate(@PathVariable("itemId") Long itemId, @PathVariable("num") Long num, HttpServletRequest request) {

        String json = null;
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if ("JT_TICKET".equals(cookie.getName())) {
                    String cookieValue = cookie.getValue();
                    Set<String> sentinels = new HashSet<String>();
                    sentinels.add("172.16.48.110:26379");
                    JedisSentinelPool pool =
                            new JedisSentinelPool("mymaster", sentinels);
                    Jedis jedis = pool.getResource();
                    json = jedis.get("" + cookieValue);
                    UUser uUser = objectMapper.readValue(json, UUser.class);
                    TbCart tbCart = new TbCart();
                    tbCart.setUserId(uUser.getId());
                    tbCart.setItemId(itemId);
                    tbCart.setNum(num);
                    cartService.cartUpdate(tbCart);

                    System.out.println(tbCart.toString());
                    return SysResult.oK();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "更新失败");
    }

    /**
     * 删除商品
     */
    @RequestMapping("/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId, HttpServletRequest request) {

        try {
            TbCart tbCart = new TbCart();
            tbCart.setUserId(UserThreadLocal.get().getId());
            tbCart.setItemId(itemId);
            cartService.deleteCart(tbCart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/cart/show.html";

    }
}
