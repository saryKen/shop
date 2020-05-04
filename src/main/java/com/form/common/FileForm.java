package com.form.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

public class FileForm {

    @Data
    public class addOneTypeForm {
        @ApiModelProperty(value = "类型",required = true)
        private String type;
        @ApiModelProperty(value = "文件，单个",required = true)
        private MultipartFile multipartFile;


        public Map<String,String> getErrorInfo(){
            Map<String,String> errorInfos = new HashMap<>();

            if(StringUtils.isEmpty(type)){
                errorInfos.put("type","类型 不能为空");
            }
            if(multipartFile==null){
                errorInfos.put("multipartFile","文件 不能为空");
            }

            return errorInfos;
        }
    }

}
