package com.jt.manage.service;

import com.jt.common.vo.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    PicUploadResult fileupload(MultipartFile uploadFile);
}
