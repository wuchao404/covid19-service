package org.open.covid19.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.open.covid19.utils.aop.annotation.Lingyejun;
import org.open.covid19.utils.aop.annotation.HttpRequestMapping;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggerApply {

    @Lingyejun(module = "http://www.cnblogs.com/lingyejun/")
    public void doLogger(String event) {
        System.out.println("service的方法，event" + event);
    }

    @HttpRequestMapping(value = "https://api.apify.com/v2/key-value-stores/moxA3Q0aZh5LosewB/records/LATEST?disableRedirect=true")
    public String sendRequest(String result){
        System.out.println("result:" + result);
        return "";
    }

    /**
     * execution指定某一个方法
     */
    public String requestLastAmericanCases(String result1, String result2){
        log.debug("result1:{},result2:{}",result1,result2);
        return "源方法（requestLastAmericanCases）";
    }



}
