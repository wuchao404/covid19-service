package org.open.covid19.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.open.covid19.api.Covid19Api;
import org.open.covid19.api.JhuApi;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.jhu.JhuCase;
import org.open.covid19.mapper.Covid19ApiMapper;
import org.open.covid19.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class Helper {
    @Autowired
    private Covid19ApiMapper covid19ApiMapper;
    @Autowired
    private Covid19Api covid19Api;
    @Autowired
    JhuApi jhuApi;

    @Async
    public void insertCasesByIso2(String slug, boolean isFromDate) {
        int result = 0;
        long countryId = covid19ApiMapper.selectCountryId(slug);
        // 某国确诊数目
        List<Case> cases =isFromDate ? covid19Api.getCasesZFromDate(slug, DateUtil.local2tz("2020-04-29")) : covid19Api.getCases(slug);
        log.debug("slug:{},countryId:{},确诊数量:{}",slug,countryId, cases.size());
        // 入库
        if (null != cases && cases.size() > 0) {
            result = covid19ApiMapper.setCasesList2country(countryId, cases);
        }
        if (result > 0) {
            log.debug("数据插入成功：{}","countryId:" + countryId);
        }
    }

    /**
     * 异步插入确诊数
     * @param countryId
     */
    @Async
    public void insertCasesFromDate(long countryId, String slug, String fromDate) {
        String days = String.valueOf(DateUtil.howManyDaysFromNow(DateUtil.stringToDate(fromDate)));
        if (Long.parseLong(days) <= 0) {
            return;
        }
        JhuCase jhuCase = jhuApi.getLastDaysCasesByCountry(days, slug);
        log.debug("jhuCase：{}",jhuCase.toString());
        log.debug("查询数量：{},countryId:{}，fromDate：{}",slug,countryId,fromDate);
        List<Case> cases = jhuCase.cast2List(countryId,slug);
        if (cases != null && cases.size() > 0) {
            //入库
            covid19ApiMapper.setCasesList2country(countryId,cases);
       }
    }
}