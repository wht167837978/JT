package com.jt.manage.controller;

import com.jt.manage.service.TbItemCatService;
import com.jt.manage.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item/cat")
public class TbitemCatController {

    @Autowired
    private TbItemCatService tbItemCatService;

    //实现商品分类目录展现
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITree> findItemCatById(@RequestParam(defaultValue = "0",value = "id") Long parentId){


        return tbItemCatService.findTtemCatRdisById(parentId);
    }
}
