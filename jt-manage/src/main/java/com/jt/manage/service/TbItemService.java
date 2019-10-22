package com.jt.manage.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.TbItem;
import com.jt.manage.pojo.TbItemDesc;

public interface TbItemService {
   EasyUIResult findItemByPage(int page, int rows);

  String findItemCatById(Long itemId);

    void saveItem(TbItem item,String desc);

    void updateitem(TbItem item,String desc);

    void updateStatus(Long[] ids, int status);

    void deleteitem(Long[] ids);

    TbItem findByIds(Long itemId);

    TbItemDesc selectByIds(Long itemId);
}
