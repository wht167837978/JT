package com.jt.manage.controller;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;
//    @RequestMapping("/file")
//    public String files(MultipartFile pic) throws IOException {
//        File file = new File("/Users/hailey/Desktop/学习相关");
//
//        if(!file.exists()){
//        file.mkdirs();
//
//    }
//    String filename=pic.getOriginalFilename();
//        pic.transferTo(new File("/Users/hailey/Desktop/学习相关/"+filename));
//        return "redirect:/file.jsp";
//}
    @RequestMapping("/pic/upload")
    @ResponseBody
    public PicUploadResult fileupload(MultipartFile uploadFile){

        return fileService.fileupload(uploadFile);

    }
}
