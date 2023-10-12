package com.laibaijiang.yygh.order.controller;

import com.laibaijiang.yygh.commong.result.Result;
import com.laibaijiang.yygh.order.service.WeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/order/weixin")
public class WeixinController {

    @Autowired
    private WeixinService weixinService;


    //生成微信支付二维码
    @GetMapping("createNative/{orderId}")
    public Result createNative(@PathVariable Long orderId) {
        Map map = weixinService.createNative(orderId);
        return Result.ok(map);
    }
}
