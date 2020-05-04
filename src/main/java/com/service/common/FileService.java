package com.service.common;

import com.form.common.FileForm;
import com.result.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {

    Result addOne(MultipartFile multipartFile);

}
