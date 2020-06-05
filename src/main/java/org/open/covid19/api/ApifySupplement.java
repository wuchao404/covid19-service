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

import java.util.List;

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
     *请求,昨天各州疫情信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/v2/key-value-stores/moxA3Q0aZh5LosewB/records/LATEST?disableRedirect=true")
    public String requestLastCase(){
        return null;
    }

    /**
     * 美国各州疫情，所有数据
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/v2/datasets/FIbyK6uHUntt2kNy3/items?format=json&clean=1")
    public String requestAllCase(){
        return null;
    }


    /**
     * 解析
     * @return
     */
    @SneakyThrows
    public AmericanCase requestLastAmericanCases(){
        String result = apifySupplement.requestLastCase();
        ObjectMapper mapper = new ObjectMapper();
        AmericanCase americanCase = mapper.readValue(result, AmericanCase.class);
        return americanCase;
    }

    /**
     * 美国所有全部历史数据
     * @return
     */
    @SneakyThrows
    public List<AmericanCase> requestAllAmericanCases(){
        String result = apifySupplement.requestAllCase();
        ObjectMapper mapper = new ObjectMapper();
        List<AmericanCase> americanCases = mapper.readValue(result, List.class);
        return americanCases;
    }

}
