package com.controller.order;

import com.config.AlipayConfig;
import com.form.order.AlipayForm;
import com.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("json/alipay")
public class AlipayController {
    @Autowired
    OrderService orderService;


    /**
     * 支付宝支付异步回调接口，通知付款结果
     * @param form
     * @return
     * @throws IOException
     */
    @RequestMapping("/callback")
    @ResponseBody
    public String callback(AlipayForm form, HttpServletRequest request)  {
        System.out.println(form);

        if (StringUtils.isEmpty(form.getOut_trade_no()) || StringUtils.isEmpty(form.getTrade_status())){
            return "failure";
        }

        orderService.updateState(form);

        return "success";
    }


    /**
     * 支付宝支付同步回调接口，回到本系统页面
     * @return
     * @throws IOException
     */
    @RequestMapping("/returnPage")
    public String returnPage()  {

        return "redirect:"+ AlipayConfig.RETURN_PAGE;
    }
}
