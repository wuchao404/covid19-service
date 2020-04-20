package org.open.covid19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author heshiyuan
 * @date 2020/4/20 10:08
 */
@SpringBootApplication
public class Covid19Application {
    public static void main(String[] args) {
        new SpringApplication(Covid19Application.class).run();
    }
}
