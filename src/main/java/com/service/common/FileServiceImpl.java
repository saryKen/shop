package com.service.common;

import com.mapper.common.FileMapper;
import com.model.common.File;
import com.result.Result;
import com.result.ResultStatus;
import com.utils.CommonUtil;
import com.utils.MultipartFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@Transactional
public class FileServiceImpl implements FileService{
    @Autowired
    FileMapper fileMapper;

    @Override
    public Result addOne(MultipartFile multipartFile) {
        // 首先检查 入参 的合法性，如有不合法输入，返回错误信息
        if(multipartFile==null){
            return Result.fail("文件不能为空", ResultStatus.ERROR_Parameter);
        }

        File file = new File();
        String fileId = CommonUtil.getLongId();
        file.setFileId(fileId);

        // 设置文件上传时的真实名
        file.setFileRealName(MultipartFileUtil.getFileRealName(multipartFile));
        // 设置文件后缀
        file.setFileSuffix(MultipartFileUtil.getFileSuffix(multipartFile));

        file.setCreateTime(new Date());

        file.setFileUrl(MultipartFileUtil.fileSave(multipartFile,"uploadFile",fileId));

        fileMapper.insertSelective(file);

        return Result.success(file.getFileUrl());
    }

}
