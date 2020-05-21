package org.open.covid19.api.config;

import feign.Logger.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置log级别
 * @author wuchao
 */
@Configuration
public class Covid19Config {
    @Bean
    Level feignLoggerLevel(){
        return Level.FULL;
    }

}
