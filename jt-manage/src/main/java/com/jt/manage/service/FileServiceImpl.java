package com.jt.manage.service;

import com.jt.common.vo.PicUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private String localPath="/Users/hailey/";
    private String img="http://image.jt.com/";
    /**
     * 1.判断上传的文件是否为图片 jpg|png|gif
     * 2.判断文件是否为恶意软件
     * 3.为了提高检索效率.采用分文件存储
     * 	 3.1 aaaaaaaa-bbbbbbbb-cccccccc-dddddddd 使用UUID
     * 	 3.2 根据时间yyyy/MM/dd
     * 4.为了防止文件名称重复  UUID + 随机数0-999
     * 5.判断文件夹是否存在,实现文件上传
     */

    @Override
    public PicUploadResult fileupload(MultipartFile uploadFile) {

        PicUploadResult picUploadResult = new PicUploadResult();
        //一.判断图片类型
        //1.获取文件名称     abc.jpg
        String filename=uploadFile.getOriginalFilename();
        //2.为了文件名称大小写一致,统统转化为小写
        String fileName = filename.toLowerCase();
        if(!fileName.matches("^.*\\.(jpg|png|gif)$")){
            //表示不是图片
           picUploadResult.setError(1);
           return picUploadResult;
        }
        //二.判断是否为恶意程序
        //2.将文件转化为图片类型,获取宽度和高度
        try {
            BufferedImage buff = ImageIO.read(uploadFile.getInputStream());
            int height = buff.getHeight();
            int width = buff.getWidth();
            if(height==0 ||width==0){
                //表示不是图片
                picUploadResult.setError(1);

                return picUploadResult;
            }
            //三 分文件存储     2018/10/01
            String DateDir =
                    new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            //3.1判断文件夹是否存在
           String pathdate= localPath+DateDir;
            File fileDir = new File(pathdate);
            if(!fileDir.exists()){
                fileDir.mkdirs();
            }

            //四.防止文件名称重复
            String uuid=UUID.randomUUID().toString().replace("-","");
            int randomNum=new Random().nextInt(1000);
            String fileType=filename.substring(filename.lastIndexOf("."));
            String realFileName=uuid+randomNum+fileType;
            //5.实现文件上传  将文件上传到本地磁盘下
            //E:/jt-upload/2018/10/01/32位+1-3位.jpg
            String localPaths=pathdate+"/"+realFileName;
            uploadFile.transferTo(new File(localPaths));
            String url=img+DateDir+"/"+realFileName;
            picUploadResult.setUrl(url);
            picUploadResult.setHeight(height+"");
            picUploadResult.setWidth(width+"");

        } catch (IOException e) {
            e.printStackTrace();
            picUploadResult.setError(1);
            return picUploadResult;
        }

        return picUploadResult;
    }
}
