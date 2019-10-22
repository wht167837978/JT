package com.jt.order.service;

import com.jt.order.pojo.TbOrder;

public interface OrderService {
    String saveorder(TbOrder order);

    String findOrderByid(String id);
}
