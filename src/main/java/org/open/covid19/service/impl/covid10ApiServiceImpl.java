package org.open.covid19.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.open.covid19.api.Covid19Api;
import org.open.covid19.entity.Case;
import org.open.covid19.mapper.Covid19ApiMapper;
import org.open.covid19.service.ICovid19Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class covid10ApiServiceImpl implements ICovid19Api {
    @Autowired
    private Covid19ApiMapper covid19ApiMapper;
    @Autowired
    private Covid19Api covid19Api;

    @Override
    public int insertCasesByIso2(String iso2) {
        long id = covid19ApiMapper.selectCountryId(iso2);
        // 某国确诊数目
        List<Case> cases =covid19Api.getCases(iso2);
        log.debug("cases.length:{}",cases.size());
        // 入库
        int result = covid19ApiMapper.setCasesList2country(id, cases);
        return result;
    }
}
