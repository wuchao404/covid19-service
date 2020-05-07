package org.open.covid19.api;

import org.open.covid19.entity.Case;
import org.open.covid19.entity.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 根据国家获取确诊数
     * @param slug  国家缩写
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/total/dayone/country/{slug}")
    List<Case> getCases(@PathVariable("slug") String slug);

    /**
     * 从某天开始，统计后面每天的病例数
     * @param slug          国家
     * @param fromDate      开始日期
     * @return
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/live/country/{slug}/status/confirmed/date/{fromDate}"
    )
    List<Case> getCasesZFromDate(@PathVariable("slug") String slug, @PathVariable("fromDate") String fromDate);
}
