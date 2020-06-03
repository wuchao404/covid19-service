package org.open.covid19.service.impl;

import org.open.covid19.utils.aop.annotation.Lingyejun;
import org.open.covid19.utils.aop.annotation.HttpRequestMapping;
import org.springframework.stereotype.Component;

@Component
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
    public void requestLastAmericanCases(){
        System.out.println("执行了");
    }

}
