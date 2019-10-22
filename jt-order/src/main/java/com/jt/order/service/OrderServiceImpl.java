package com.jt.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.order.mapper.OrderItemMapper;
import com.jt.order.mapper.OrderItemShippingMapper;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.pojo.TbOrder;
import com.jt.order.pojo.TbOrderItem;
import com.jt.order.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderItemShippingMapper orderItemShippingMapper;

    private static ObjectMapper objectMapper=new ObjectMapper();

    public String saveorder(TbOrder order) {
        Date date=new Date();
        String orderid=""+order.getUserId()+System.currentTimeMillis();
        order.setOrderId(orderid);
        order.setCreated(date);
        order.setUpdated(date);
        order.setStatus(1L);
        orderMapper.insert(order);
        System.out.println(order+"订单信息已入库");
        TbOrderShipping orderShipping = order.getOrderShipping();
        orderShipping.setOrderId(orderid);
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderItemShippingMapper.insert(orderShipping);
        System.out.println(orderShipping+"收货地址已入库");
        List<TbOrderItem> orderItems = order.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
            orderItem.setOrderId(orderid);
            orderItem.setCreated(date);
            orderItem.setUpdated(date);
            orderItemMapper.insert(orderItem);

        }
        System.out.println(orderItems+"商品信息已入库");
        return orderid;
    }

    public String findOrderByid(String id) {
        String order=null;

        try {
            TbOrder tbOrder = orderMapper.selectByPrimaryKey(id);
            TbOrderShipping tbOrderShipping = orderItemShippingMapper.selectByPrimaryKey(id);
            TbOrderItem tbOrderItem = new TbOrderItem();
            tbOrderItem.setOrderId(id);
            List<TbOrderItem> orderList = orderItemMapper.select(tbOrderItem);
            tbOrder.setOrderItems(orderList);
            tbOrder.setOrderShipping(tbOrderShipping);
            order = objectMapper.writeValueAsString(tbOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }
}
