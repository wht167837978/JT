package com.jt.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jt.web.pojo.TbCart;
import com.jt.web.vo.HttpClientService;
import com.jt.web.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private HttpClientService httpClient;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public List<TbCart> findCartByUserId(Long id) {
        String url = "http://cart.jt.com/cart/query/" + id;
        String resultJson = httpClient.doGet(url);
        List<TbCart> cartList = null;
        try {
            SysResult sysResult = objectMapper.readValue(resultJson, SysResult.class);
            cartList = (List<TbCart>) sysResult.getData();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return cartList;
    }

    public void SaveCart(TbCart cart) {
        String url = "http://cart.jt.com/cart/save";
        Map<String, String> paraes = new HashMap<String, String>();
        paraes.put("userId", cart.getUserId() + "");
        paraes.put("itemId", cart.getItemId() + "");
        paraes.put("itemTitle", cart.getItemTitle());
        paraes.put("itemImage", cart.getItemImage());
        paraes.put("itemPrice", cart.getItemPrice() + "");
        paraes.put("num", cart.getNum() + "");
        httpClient.doPost(url, paraes);


    }

    public void cartUpdate(TbCart tbCart) {
        //String url="http://cart.jt.com/cart/update/"+tbCart.getUserId()+"/"+tbCart.getItemId()+"/"+tbCart.getNum();
        String url="http://cart.jt.com/cart/update/num";

        String json = null;
        try {
            json = objectMapper.writeValueAsString(tbCart);
            Map<String, String> paraes = new HashMap<String, String>();
            paraes.put("cartJson",json);
            httpClient.doGet(url,paraes);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void deleteCart(TbCart tbCart) {
        String url="http://cart.jt.com/cart/delete/"+tbCart.getUserId()+"/"+tbCart.getItemId();

        httpClient.doGet(url);
    }
}
