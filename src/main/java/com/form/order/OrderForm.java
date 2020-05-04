package com.form.order;

import com.form.PageForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class OrderForm {

    @Data
    public static class findByIdForm{
        @ApiModelProperty(value = "id",required = true)
        @NotNull(message = "id 不能为空")
        private String id;

    }

    @Data
    public static class listForm extends PageForm{
		@ApiModelProperty(value = "景点信息,模糊匹配")
		private String scenicSpotInfo;

		@ApiModelProperty(value = "用户id,模糊匹配",hidden = true)
		private Integer userId;
    }

    @Data
    public static class addForm {
		@ApiModelProperty(value = "景点id",required = true)
		@NotBlank(message = "景点id 不能为空")
		private String scenicSpotId;
		@ApiModelProperty(value = "票数",required = true)
		@NotNull(message = "票数 不能为空")
		private Integer ticketNum;


    }

	@Data
    public class statForm {
		@ApiModelProperty(value = "开始时间,yyyy-MM-dd",required = true)
		@NotNull(message = "开始时间 不能为空")
		private String startTime;
		@ApiModelProperty(value = "结束时间,yyyy-MM-dd",required = true)
		@NotNull(message = "结束时间 不能为空")
		private String endTime;

    }
}
