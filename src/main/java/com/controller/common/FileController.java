package com.controller.common;

import com.result.Result;
import com.service.common.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "管理端-图片上传",tags = {"管理端-图片上传"})
@RestController
@RequestMapping("json/admin/file")
public class FileController {
    @Autowired
    FileService fileService;

    @ApiOperation(value = "新增单个文件",notes = "新增单个文件，上传成功返回可预览的url")
    @PostMapping("/addOne")
    public Result addOne(MultipartFile multipartFile){

        return fileService.addOne(multipartFile);
    }

}
