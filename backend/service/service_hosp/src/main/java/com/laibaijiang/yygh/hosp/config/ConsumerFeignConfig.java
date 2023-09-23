package com.laibaijiang.yygh.hosp.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerFeignConfig {

    @Bean
    public Request.Options feignRequestOptions() {
        // 设置连接超时和读取超时时间（此示例设置为6000毫秒，即6秒）
        return new Request.Options(6000, 6000);
    }
}

