package com.jt.web.service;

import com.jt.web.pojo.TbItem;
import com.jt.web.pojo.TbItemDesc;
import org.springframework.stereotype.Service;


public interface itemservice {
    TbItem findTtemById(Long itemid);
    TbItemDesc findTemdescById(Long itemid);
}
