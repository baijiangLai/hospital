package com.laibaijiang.yygh.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lbj.yygh.model.order.OrderInfo;
import com.lbj.yygh.model.order.PaymentInfo;

import java.util.Map;

public interface PaymentService extends IService<PaymentInfo> {


    void savePaymentInfo(OrderInfo order, Integer status);

    void paySuccess(String outTradeNo, Map<String, String> resultMap);
}
