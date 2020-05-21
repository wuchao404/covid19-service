package org.open.covid19.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.Country;
import org.open.covid19.mapper.AnalysisCaseMapper;
import org.open.covid19.mapper.CountriesMapper;
import org.open.covid19.service.IAnalysisCasesService;
import org.open.covid19.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Slf4j
public class AnalysisCaseImpl implements IAnalysisCasesService{
    @Autowired
    AnalysisCaseMapper analysisCaseMapper;
    @Autowired
    CountriesMapper countriesMapper;
    @Autowired
    Helper helper;

    @Override
    public List<Map<String, Integer>> updateRecordSize2Country() {
        List<Country> countries = countriesMapper.getAll();
        if (null == countries || countries.size() <= 0) {
            return Lists.newArrayList();
        }
        List<Map<String, Integer>> errorList = new ArrayList<>();
        countries.forEach(country -> {
            Integer countryId = country.getId();
            int size = analysisCaseMapper.getCasesSizeByCountry(countryId);
            int result = analysisCaseMapper.updateRecordCountById(countryId, size);
            if (result <= 0) { // 插入失败
                Map<String, Integer> map = new HashMap<>();
                map.put(country.getSlug(),size);
                errorList.add(map);
            }
        });
        return errorList;
    }

    @Override
    public int updateRecordOptimizer() {
        // 1.查询所有记录
        // 2.批量插入国家表
        List<Country> allRecords = analysisCaseMapper.getAllRecords();
        log.debug("allRecords:{}",allRecords.toString());
        int result = null != allRecords && allRecords.size() > 0 ?
                analysisCaseMapper.updateAllRecords(allRecords) : 0;
        return result;
    }

    /**
     * 使用begin-end优化
     * @return int
     */
    @Override
    public int updateBeginEnd() {
        List<Country> allRecords = analysisCaseMapper.getAllRecords();
        int result = null != allRecords && allRecords.size() > 0 ?
                analysisCaseMapper.updateRecordWithBeginEnd(allRecords) : 0;
        return result;
    }

    /**
     * 根据日期批量插入数据
     */
    @Override
    public void insertRecordsFromDate() {
        List<Case> records = analysisCaseMapper.selectLessThanSizeRecords(10);
        if (null != records && records.size() > 0){
            records.forEach(record ->{
                helper.insertCasesFromDate(
                        record.getCountryId(),
                        record.getSlug(),
                        DateUtil.local2tz(record.getDate()));
            });
        }
    }


}
