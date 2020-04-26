package org.open.covid19;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author heshiyuan
 * @date 2020/4/20 10:08
 */
@SpringBootApplication
@EnableFeignClients
public class Covid19Application {
    public static void main(String[] args) {
        new SpringApplication(Covid19Application.class).run();
    }
}
