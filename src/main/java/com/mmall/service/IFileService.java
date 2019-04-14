package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lll on 19/3/31.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);

}
