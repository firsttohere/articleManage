package com.xzedu.article.controller;

import com.xzedu.article.common.Result;
import com.xzedu.article.utils.OOSUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName : FileUploadController
 * @Description : FileUploadController
 * @Author : Xxxxx
 * @Date: 2023-11-19 14:22
 */
@RestController
@CrossOrigin
public class FileUploadController {
    // 文件上传接口 上传文件到阿里云
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();// 原始文件名
        String fileName = UUID.randomUUID().toString() + originalFilename;
        String url = OOSUtil.uploadFile(fileName, file.getInputStream());
        if (!StringUtils.hasLength(url)) {
            return Result.failure("文件上传失败");
        }
        return Result.success(url);
    }
}
