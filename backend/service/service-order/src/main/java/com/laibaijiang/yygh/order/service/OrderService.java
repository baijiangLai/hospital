package com.laibaijiang.yygh.order.service;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbj.yygh.model.order.OrderInfo;
import com.lbj.yygh.vo.order.OrderQueryVo;

import java.util.Map;

public interface OrderService extends IService<OrderInfo> {


    Long saveOrder(String scheduleId, Long patientId);

    OrderInfo getOrder(String orderId);

    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

    Boolean cancelOrder(Long orderId);

    // 患者就诊通知
    void patientTips();

}
