package org.open.covid19.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.open.covid19.entity.apify.AmericanCase;
import org.open.covid19.utils.aop.annotation.HttpCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * APIfy的补充，有些接口无法使用FeignClient调用
 * @author wuchao
 */
@Component
@HttpCustom(name = "ITestApify", url = "https://api.apify.com")
@Slf4j
public class ApifySupplement {
    @Autowired
    ApifySupplement apifySupplement;
    /**
     *请求
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/v2/key-value-stores/moxA3Q0aZh5LosewB/records/LATEST?disableRedirect=true")
    public String requestHttp(){
        return null;
    }

    /**
     * 解析
     * @return
     */
    @SneakyThrows
    public AmericanCase requestAllAmericanCases(){
        String result = apifySupplement.requestHttp();
        ObjectMapper mapper = new ObjectMapper();
        AmericanCase americanCase = mapper.readValue(result, AmericanCase.class);
        return americanCase;
    }
}
