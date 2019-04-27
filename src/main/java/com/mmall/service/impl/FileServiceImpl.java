package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by lll on 19/3/31.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //设置上传文件名,避免name相同导致覆盖
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;

        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        //如果文件不存在
        if(!fileDir.exists()){
            //首先赋予可写的权限
            fileDir.setWritable(true);
            //创建所有文件夹,mkdir仅创建当前的
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件已上传成功
            //将targetFile上传到FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //上传完成后,删除upload下面的文件(在tomcat中)
            targetFile.delete();

        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }
}
