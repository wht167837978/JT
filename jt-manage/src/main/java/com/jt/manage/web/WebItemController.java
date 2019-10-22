package com.jt.manage.web;

import com.jt.manage.pojo.TbItem;
import com.jt.manage.pojo.TbItemDesc;
import com.jt.manage.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class WebItemController {
    @Autowired
    private TbItemService tbItemService;
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem finditemById(@PathVariable Long itemId){

        return tbItemService.findByIds(itemId);
    }
    @RequestMapping("/item/desc/{itemId}")
    @ResponseBody
    public TbItemDesc finditemdescById(@PathVariable Long itemId){

        return tbItemService.selectByIds(itemId);
    }
}
