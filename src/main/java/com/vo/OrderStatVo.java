package com.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderStatVo {
    @ApiModelProperty("订单总量")
    private int orderCount;

    @ApiModelProperty("总金额")
    private double money;
}
