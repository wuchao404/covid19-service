package org.open.covid19.api.fallback;

import com.google.common.collect.Lists;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.open.covid19.api.Covid19Api;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.Country;
import org.open.covid19.mapper.ApiFallbackMapper;
import org.open.covid19.mapper.Covid19ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 错误处理
 * @author wuchao
 */
@Component
@Slf4j
public class Covid19ApiFallback implements FallbackFactory<Covid19Api> {
    @Autowired
    ApiFallbackMapper apiFallbackMapper;
    @Autowired
    Covid19ApiMapper covid19ApiMapper;

    @Override
    public Covid19Api create(Throwable cause) {
        log.debug("接口执行错误：{}",cause.toString());
        return new Covid19Api() {
            @Override
            public List<Country> getCountries() {
                return null;
            }

            @Override
            public List<Case> getCases(String slug) {
                long countryId = covid19ApiMapper.selectCountryId(slug);
                StringBuffer sb = new StringBuffer();
                sb.append("countryId=").append(countryId)
                        .append("&slug=").append(slug)
                        .append("&messge=").append(cause.getMessage());
                apiFallbackMapper.insertErrorMessage("/total/dayone/country/{slug}", sb.toString());
                return Lists.newArrayList();
            }

            @Override
            public List<Case> getCasesZFromDate(String slug, String fromDate) {
                return null;
            }
        };
    }
}
