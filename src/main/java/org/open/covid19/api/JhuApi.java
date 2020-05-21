package org.open.covid19.api;

import feign.Headers;
import org.open.covid19.api.fallback.JhuApiFallback;
import org.open.covid19.entity.jhu.JhuCase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 约翰·霍普金斯大学接口
 * @author wuchao
 */
@FeignClient(
        name = "jhuApi",
        url = "http://corona.lmao.ninja",
        fallbackFactory = JhuApiFallback.class
)
public interface JhuApi {
    /**
     * 查询某国最近x天的确诊数据
     * @param lastDays
     * @param slug
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/v2/historical/{slug}?lastdays={lastDays}")
    @Headers({"Host: http://corona.lmao.ninja"})
    JhuCase getLastDaysCasesByCountry(@PathVariable String lastDays, @PathVariable String slug);
}
