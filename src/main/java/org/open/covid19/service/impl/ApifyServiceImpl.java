package org.open.covid19.service.impl;

import org.open.covid19.api.ApifyApi;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.apify.AmericanCase;
import org.open.covid19.entity.jhu.ProvinceEntity;
import org.open.covid19.mapper.Covid19ApiMapper;
import org.open.covid19.service.IApifyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApifyServiceImpl implements IApifyService {
    @Autowired
    ApifyApi apifyApi;
    @Autowired
    Covid19ApiMapper covid19ApiMapper;

    /**
     * 插入美国所有州的数据
     */
    @Override
    public void insertAllAmericanStatesCase() {
        List<AmericanCase> americanCases = apifyApi.getAllAmericanStateCases();
        Map<String, Long> map = getUSProvinceIdMap();
        if (null != americanCases && americanCases.size() > 0) {
            americanCases.forEach(americanCase -> {
                List<Case> cases = americanCase.cast2List(map,15);
                americanCase.setCases(cases);
            });
        }

    }

    /**
     * 把省份数组转换为map集合，key为省份名称，value为省份id
     * @return
     */
    private Map<String,Long> getUSProvinceIdMap(){
        List<ProvinceEntity> provinceList = covid19ApiMapper.selectUsStates();
        Map<String,Long> map = new HashMap<>(10);
        if (null != provinceList && provinceList.size() > 0) {
            provinceList.forEach(p -> {
                map.put(p.getProvinceName(),p.getProvinceId());
            });
        }
        return map;
    }
}
