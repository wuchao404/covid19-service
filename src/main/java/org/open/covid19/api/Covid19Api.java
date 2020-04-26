package org.open.covid19.api;

import org.open.covid19.entity.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 调用网站接口
 */
@FeignClient(name = "covid19api",url = "https://api.covid19api.com")
public interface Covid19Api {
    /**
     * 国家列表
     * @return list
     */
    @RequestMapping(method = RequestMethod.GET, value = "/countries")
    List<Country> getCountries();
}
