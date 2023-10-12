package com.laibaijiang.yygh.order.service.impl;

import com.github.wxpay.sdk.WXPayUtil;
import com.laibaijiang.yygh.order.service.OrderService;
import com.laibaijiang.yygh.order.service.PaymentService;
import com.laibaijiang.yygh.order.service.WeixinService;
import com.laibaijiang.yygh.order.utils.ConstantPropertiesUtils;
import com.laibaijiang.yygh.order.utils.HttpClient;
import com.lbj.yygh.enums.PaymentTypeEnum;
import com.lbj.yygh.model.order.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class WeixinServiceImpl implements WeixinService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RedisTemplate redisTemplate; //支付两个小时有效，使用redis进行缓存


    //生成微信支付二维码
    @Override
    public Map createNative(Long orderId) {
        try {
            //从redis获取数据
            Map payMap = (Map)redisTemplate.opsForValue().get(orderId.toString());
            if(payMap != null) {
                return payMap;
            }

            //1 根据orderId获取订单信息
            OrderInfo order = orderService.getById(orderId);
            //2 向支付记录表添加信息
            paymentService.savePaymentInfo(order, PaymentTypeEnum.WEIXIN.getStatus());
            //3设置参数，
            //把参数转换xml格式，使用商户key进行加密
            Map paramMap = new HashMap();
            paramMap.put("appid", ConstantPropertiesUtils.APPID);
            paramMap.put("mch_id", ConstantPropertiesUtils.PARTNER);
            paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
            String body = order.getReserveDate() + "就诊"+ order.getDepname();
            paramMap.put("body", body);
            paramMap.put("out_trade_no", order.getOutTradeNo());
            //paramMap.put("total_fee", order.getAmount().multiply(new BigDecimal("100")).longValue()+"");
            paramMap.put("total_fee", "1"); //为了测试，统一写成这个值
            paramMap.put("spbill_create_ip", "127.0.0.1");
            paramMap.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify");
            paramMap.put("trade_type", "NATIVE");

            //4 调用微信生成二维码接口,httpclient调用
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //设置map参数
            client.setXmlParam(WXPayUtil.generateSignedXml(paramMap,ConstantPropertiesUtils.PARTNERKEY));
            client.setHttps(true);
            client.post();
            //5 返回相关数据
            String xml = client.getContent();
            //转换map集合
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            System.out.println("resultMap:"+resultMap);
            //6 封装返回结果集
            Map map = new HashMap<>();
            map.put("orderId", orderId);
            map.put("totalFee", order.getAmount());
            map.put("resultCode", resultMap.get("result_code"));
            map.put("codeUrl", resultMap.get("code_url")); //二维码地址

            //支付二维码放入redis，有效期两个小时
            if(resultMap.get("result_code") != null) {
                redisTemplate.opsForValue().set(orderId.toString(),map,120, TimeUnit.MINUTES);
            }

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
