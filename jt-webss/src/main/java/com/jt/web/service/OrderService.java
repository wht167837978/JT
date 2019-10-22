package com.jt.web.service;

import com.jt.web.pojo.TbOrder;

public interface OrderService {
    String saveorder(TbOrder order);

    TbOrder fiandOrderByid(String id);
}
