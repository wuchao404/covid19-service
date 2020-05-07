package org.open.covid19.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.open.covid19.api.Covid19Api;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.Country;
import org.open.covid19.mapper.Covid19ApiMapper;
import org.open.covid19.service.ICovid19ApiService;
import org.open.covid19.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
public class Covid16ApiServiceImpl implements ICovid19ApiService {
    @Autowired
    private Covid19ApiMapper covid19ApiMapper;
    @Autowired
    private Covid19Api covid19Api;
    @Autowired
    private Helper helper;

    @Async
    int insertCasesByIso2(String iso2, boolean isFromDate) {
        int result = 0;
        long id = covid19ApiMapper.selectCountryId(iso2);
        // 某国确诊数目
        List<Case> cases =isFromDate ? covid19Api.getCasesZFromDate(iso2, DateUtil.local2tz("2020-05-06")) : covid19Api.getCases(iso2);
        log.debug("该国家确诊数量:{}",cases.size());
        // 入库
        if (null != cases && cases.size() > 0) {
            result = covid19ApiMapper.setCasesList2country(id, cases);
        }
        if (result > 0) {
            log.debug("数据插入成功：{}","iso2:" + iso2);
        }
        return result;
    }

    void insertCases(final boolean isFromDate) {
        List<Country> countryList = covid19ApiMapper.getCountryList();
        if (null == countryList || countryList.size() <= 0) {
            return;
        }
        countryList.forEach(country -> {
            helper.insertCasesByIso2(country.getSlug(),isFromDate);
        });
    }
    @Override
    public void insertAllCases() {
        insertCases(false);
    }

    @Override
    public void insertCasesFromDate() {
        insertCases(true);
    }

}
