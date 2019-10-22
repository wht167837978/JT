package com.jt.manage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web")
public class JsonpController {
    @RequestMapping(value = "/testJSONP",produces = "test/html;charset=utf-8")
    @ResponseBody
    public String testJsop(String callback) throws JsonProcessingException {
        User user = new User();
        user.setId(233);
        user.setName("dsafa");
        user.setAge(23);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        return callback+"("+json+")";

    }
}
