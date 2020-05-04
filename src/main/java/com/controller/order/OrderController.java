package com.controller.order;

import com.form.PageForm;
import com.form.order.OrderForm;
import com.result.Result;
import com.service.order.OrderService;
import com.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Api(value = "用户端-订单 操作接口",tags = {"用户端-订单 操作接口"})
@RestController
@RequestMapping("json/user/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @ApiOperation(value = "根据id查询订单详情信息",notes = "根据id查询 订单 详情信息")
    @GetMapping("/findById")
    public Result findById(@Valid OrderForm.findByIdForm form){

        return orderService.findById(form);
    }

    @ApiOperation(value = "查询订单列表",notes = "查询满足条件的 订单 列表")
    @GetMapping("/list")
    public Result list(@Valid PageForm form){
        OrderForm.listForm listForm = new OrderForm.listForm();
        BeanUtils.copyProperties(form,listForm);
        listForm.setUserId(UserUtil.getUserInfo().getUserId());

        return orderService.list(listForm);
    }

    @ApiOperation(value = "新增订单",notes = "新增 订单")
    @PostMapping("/add")
    public Result add(@Valid OrderForm.addForm form, HttpServletResponse httpResponse) throws IOException {

        return orderService.add(form,httpResponse);
    }


}
