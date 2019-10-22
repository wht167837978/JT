package com.jt.web.Controller;

import com.jt.web.pojo.TbItem;
import com.jt.web.pojo.TbItemDesc;
import com.jt.web.service.itemservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/items")
public class httpitemController {
    @Autowired
    private itemservice itemservice;

    @RequestMapping("/{itemId}")
    public String finditemById(@PathVariable("itemId") Long itemid, Model model){
        System.out.println(itemid);
        TbItem tbitem = itemservice.findTtemById(itemid);
        System.out.println(tbitem);
        model.addAttribute("item",tbitem);
        TbItemDesc tbitemdesc = itemservice.findTemdescById(itemid);
        model.addAttribute("itemDesc",tbitemdesc);
        return "item";
    }
}
