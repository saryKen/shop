package com.mapper.order;

import com.form.order.OrderForm;
import com.vo.OrderStatVo;

public interface OrderStatMapper {


    OrderStatVo stat(OrderForm.statForm form);

}