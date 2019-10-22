package com.jt.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;
import com.jt.order.pojo.TbOrder;
import com.jt.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
private static ObjectMapper objectMapper=new ObjectMapper();


    @RequestMapping("/order/create")
    @ResponseBody
    public SysResult submit(String orderJson) {
        try {
            TbOrder Order = objectMapper.readValue(orderJson, TbOrder.class);
            String orderid = orderService.saveorder(Order);
            if(!StringUtils.isEmpty(orderid)){
                return SysResult.oK(orderid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201,"提交失败");
    }
    @RequestMapping("/order/query/{id}")
    @ResponseBody
    public String findOrderById(@PathVariable String id){

return orderService.findOrderByid(id);
    }
}
