package org.open.covid19.api;

import org.open.covid19.entity.apify.AmericanCase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 调用Apify接口
 * @author wuchao
 */
@FeignClient(
        name = "apifyApi",
        url = "https://api.apify.com"
)
public interface ApifyApi {
    /**
     * 美国各州全部历史数据
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/v2/datasets/FIbyK6uHUntt2kNy3/items?format=json&clean=1")
    List<AmericanCase> getAllAmericanStateCases();
}
