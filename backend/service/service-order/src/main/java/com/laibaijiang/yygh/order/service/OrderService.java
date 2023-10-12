package com.laibaijiang.yygh.order.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.lbj.yygh.model.order.OrderInfo;

import java.util.Map;

public interface OrderService extends IService<OrderInfo> {


    Long saveOrder(String scheduleId, Long patientId);
}
