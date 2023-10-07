package com.laibaijiang.yygh.msm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils implements InitializingBean {

//    @Value("${uni.sms.regionId}")
//    private String regionId;

    @Value("${uni.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${uni.sms.secret}")
    private String secret;

//    public static String REGION_Id;
    public static String ACCESS_KEY_ID;
    public static String SECRECT;

    @Override
    public void afterPropertiesSet() throws Exception {
//        REGION_Id=regionId;
        ACCESS_KEY_ID=accessKeyId;
        SECRECT=secret;
    }
}

