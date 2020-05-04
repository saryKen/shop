package com.form.product;

import com.form.PageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ScenicSpotForm {

    @Data
    public static class findByIdForm{
        @ApiModelProperty(value = "id",required = true)
        @NotNull(message = "id 不能为空")
        private String id;

    }

    @Data
    public static class listForm extends PageForm{
		@ApiModelProperty(value = "景点名称,模糊匹配")
		private String name;


    }

    @Data
    public static class addForm {
		@ApiModelProperty(value = "景点名称",required = true)
		@NotBlank(message = "景点名称 不能为空")
		private String name;
		@ApiModelProperty(value = "景点简介",required = true)
		@NotBlank(message = "景点简介 不能为空")
		private String introduction;
		@ApiModelProperty(value = "景点地址",required = true)
		@NotBlank(message = "景点地址 不能为空")
		private String address;
		@ApiModelProperty(value = "景点票价",required = true)
		@NotNull(message = "景点票价 不能为空")
		private BigDecimal ticketPrice;
		@ApiModelProperty(value = "备注信息",required = true)
		@NotBlank(message = "备注信息 不能为空")
		private String remark;

		@ApiModelProperty(value = "图片url，多个用逗号隔开")
		private List<String> imgUrls = new ArrayList<>();



    }

    @Data
    public static class updateForm {
		@ApiModelProperty(value = "景点id",required = true)
		@NotBlank(message = "景点id 不能为空")
		private String scenicSpotId;
		@ApiModelProperty(value = "景点名称")
		private String name;
		@ApiModelProperty(value = "景点简介")
		private String introduction;
		@ApiModelProperty(value = "景点地址")
		private String address;
		@ApiModelProperty(value = "景点票价")
		private BigDecimal ticketPrice;
		@ApiModelProperty(value = "备注信息")
		private String remark;

		@ApiModelProperty(value = "图片url，多个用逗号隔开")
		private List<String> imgUrls = new ArrayList<>();

    }

    @Data
    public static class deleteForm {
        @ApiModelProperty(value = "id,多个id用逗号分隔",required = true)
        @NotEmpty(message = "至少传入一个id")
        private List<String> ids;

    }
}
