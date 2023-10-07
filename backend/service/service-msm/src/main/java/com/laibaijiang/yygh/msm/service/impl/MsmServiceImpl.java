package com.laibaijiang.yygh.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
//import com.aliyuncs.CommonRequest;
//import com.aliyuncs.CommonResponse;
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.http.MethodType;
//import com.aliyuncs.profile.DefaultProfile;
import com.apistd.uni.Uni;
import com.apistd.uni.UniException;
import com.apistd.uni.UniResponse;
import com.apistd.uni.sms.UniMessage;
import com.apistd.uni.sms.UniSMS;
import com.laibaijiang.yygh.msm.service.MsmService;
import com.laibaijiang.yygh.msm.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phone, String code) {
        //判断手机号是否为空
        if(StringUtils.isEmpty(phone)) {
            return false;
        }

//        //整合阿里云短信服务
//        //设置相关参数
//        DefaultProfile profile = DefaultProfile.
//                getProfile(ConstantPropertiesUtils.REGION_Id,
//                        ConstantPropertiesUtils.ACCESS_KEY_ID,
//                        ConstantPropertiesUtils.SECRECT);
//        IAcsClient client = new DefaultAcsClient(profile);
//        CommonRequest request = new CommonRequest();
//        //request.setProtocol(ProtocolType.HTTPS);
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//
//        //手机号
//        request.putQueryParameter("PhoneNumbers", phone);
//        //签名名称
//        request.putQueryParameter("SignName", "我的谷粒在线教育网站");
//        //模板code
//        request.putQueryParameter("TemplateCode", "SMS_180051135");
//        //验证码  使用json格式   {"code":"123456"}
//        Map<String,Object> param = new HashMap();
//        param.put("code",code);
//        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
//
//        //调用方法进行短信发送
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//            return response.getHttpResponse().isSuccess();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }


        //使用uni-sms服务
        Uni.init(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.SECRECT);
        // 设置自定义参数 (变量短信)
        Map<String, String> templateData = new HashMap<>();
        templateData.put("code", code);

        // 构建信息
        UniMessage message = UniSMS.buildMessage()
                .setTo(phone)
                .setSignature("UniSMS")
                .setTemplateId("login_tmpl")
                .setTemplateData(templateData);

        // 发送短信
        try {
            UniResponse res = message.send();
            if (res.code.equals("200")) {
                return true;
            }
        } catch (UniException e) {
            System.out.println("Error: " + e);
            System.out.println("RequestId: " + e.requestId);
        }


        return false;
    }

//    private boolean send(String phone, Map<String,Object> param) {
//        //判断手机号是否为空
//        if(StringUtils.isEmpty(phone)) {
//            return false;
//        }
//        //整合阿里云短信服务
//        //设置相关参数
//        DefaultProfile profile = DefaultProfile.
//                getProfile(ConstantPropertiesUtils.REGION_Id,
//                        ConstantPropertiesUtils.ACCESS_KEY_ID,
//                        ConstantPropertiesUtils.SECRECT);
//        IAcsClient client = new DefaultAcsClient(profile);
//        CommonRequest request = new CommonRequest();
//        //request.setProtocol(ProtocolType.HTTPS);
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//
//        //手机号
//        request.putQueryParameter("PhoneNumbers", phone);
//        //签名名称
//        request.putQueryParameter("SignName", "我的谷粒在线教育网站");
//        //模板code
//        request.putQueryParameter("TemplateCode", "SMS_180051135");
//
//        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
//
//        //调用方法进行短信发送
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//            return response.getHttpResponse().isSuccess();
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
