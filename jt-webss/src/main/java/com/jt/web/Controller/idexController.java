package com.jt.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class idexController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }


}
