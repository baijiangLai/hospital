package com.laibaijiang.yygh.order.service;

import java.util.Map;

public interface WeixinService {


    Map createNative(Long orderId);

    Map<String, String> queryPayStatus(Long orderId);
}
