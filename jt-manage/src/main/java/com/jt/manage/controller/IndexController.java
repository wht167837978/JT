package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
    @RequestMapping("index")
    public String indexs(){

        return "index";
    }

    //ResFot格式跳转页面
    @RequestMapping("/page/{moduleName}")
    public String item_add(@PathVariable("moduleName") String moduleName){
        return moduleName;
    }



}
