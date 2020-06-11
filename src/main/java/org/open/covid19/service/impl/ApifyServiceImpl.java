package org.open.covid19.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.open.covid19.api.ApifyApi;
import org.open.covid19.api.ApifySupplement;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.FallbackEntity;
import org.open.covid19.entity.apify.AmericanCase;
import org.open.covid19.entity.jhu.ProvinceEntity;
import org.open.covid19.mapper.ApiFallbackMapper;
import org.open.covid19.mapper.Covid19ApiMapper;
import org.open.covid19.service.IApifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Slf4j
public class ApifyServiceImpl implements IApifyService {
    @Autowired
    ApifyApi apifyApi;
    @Autowired
    ApifySupplement apifySupplement;
    @Autowired
    Covid19ApiMapper covid19ApiMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ApiFallbackMapper apiFallbackMapper;


    /**
     * 插入美国所有州的数据
     */
    @SneakyThrows
    @Override
    public void insertAllAmericanStatesCase() {
        String result = apifySupplement.requestAllCase();
        List<AmericanCase> americanCases = objectMapper.readValue(result, new TypeReference<List<AmericanCase>>() {});
        Map<String, Long> map = getUSProvinceIdMap();
        List<Case> cases = new ArrayList<>();
        List<FallbackEntity> fallbackEntityList = new ArrayList<>();
        if (null != americanCases && americanCases.size() > 0) {

            log.debug("americanCases数量：{}",americanCases.size());
            americanCases.forEach(americanCase -> {
                americanCase.cast2List(map,15);
                // 加入到新数组
                cases.addAll(americanCase.getCases());
                fallbackEntityList.addAll(americanCase.getFallbackEntities());
            });
        }
        // 批量入库
        if (null != cases && cases.size() > 0) {
            covid19ApiMapper.batchInsertCaseList(cases);
        }
        // 批量入库
        if (null != fallbackEntityList && fallbackEntityList.size() > 0) {
            apiFallbackMapper.batchInsertErrorList(fallbackEntityList);
        }

    }

    /**
     * 把省份数组转换为map集合，key为省份名称，value为省份id
     * @return
     */
    public Map<String,Long> getUSProvinceIdMap(){
        List<ProvinceEntity> provinceList = covid19ApiMapper.selectUsStates();
        Map<String,Long> map = new HashMap<>(10);
        if (null != provinceList && provinceList.size() > 0) {
            log.debug("美国各州:{}",provinceList.toString());
            provinceList.forEach(p -> {
                map.put(p.getProvinceName(),p.getProvinceId());
            });
        }
        return map;
    }
}
