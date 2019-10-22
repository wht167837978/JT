package com.jt.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.web.pojo.TbOrder;
import com.jt.web.vo.HttpClientService;
import com.jt.web.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private HttpClientService httpClient;
private static ObjectMapper objectMapper=new ObjectMapper();
    public String saveorder(TbOrder order) {
       String orderId=null;
        try {
            String url="http://order.jt.com/order/create";
            String orderJson = objectMapper.writeValueAsString(order);
            HashMap<String, String> paraes = new HashMap<String, String>();
            paraes.put("orderJson",orderJson);
            String Result = httpClient.doPost(url, paraes);
            SysResult sysResult = objectMapper.readValue(Result, SysResult.class);
            if(sysResult.getStatus()==200){
                orderId=(String)sysResult.getData();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public TbOrder fiandOrderByid(String id) {
        TbOrder order=null;

        try {
            String url="http://order.jt.com/order/query/"+id;
            String oderJson = httpClient.doGet(url);
            order = objectMapper.readValue(oderJson, TbOrder.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }
}
