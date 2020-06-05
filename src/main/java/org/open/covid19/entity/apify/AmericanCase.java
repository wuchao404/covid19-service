package org.open.covid19.entity.apify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanMap;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.FallbackEntity;
import org.open.covid19.utils.DateUtil;
import org.open.covid19.utils.NumberUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 美国各州确诊数据.使用`JsonIgnoreProperties`忽略位置参数.
 * 记录数据过程中，如果有错误数据，则将错误信息入库
 * @author wuchao
 */
@Data
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmericanCase {
    @JsonProperty("lastUpdatedAtApify")
    private String lastUpdatedAtApify;
    @JsonProperty("lastUpdatedAtSource")
    private String lastUpdatedAtSource;
    @JsonProperty("totalCases")
    private Integer totalCases;
    @JsonProperty("totalDeaths")
    private Integer totalDeaths;
    @JsonProperty("casesByState")
    private List<CasesByState> casesByStates;
    /**
     * 转换后的实体
     */
    private List<Case> cases;
    /**
     * 错误信息，转换过程中有些数据不符合规范，这些数据要记录到库中
     */
    private List<FallbackEntity> fallbackEntities;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CasesByState{
        @JsonProperty("casesReported")
        private String casesReported;
        @JsonProperty("communityTransmission")
        private String communityTransmission;
        @JsonProperty("name")
        private String name;
        @JsonProperty("range")
        private String range;
    }

    public String getLastUpdatedAtApify() {
        return DateUtil.format(lastUpdatedAtApify,DateUtil.FORMAT_TZ000, DateUtil.FORMAT_YYYY_MM_DD);
    }

    public String getLastUpdatedAtSource() {
        return DateUtil.format(lastUpdatedAtSource,DateUtil.FORMAT_TZ000, DateUtil.FORMAT_YYYY_MM_DD);
    }

    /**
     * 转换实体
     * @param map key为省份名称，value为省份id
     * @return
     */
    public void cast2List(Map<String, Long> map,long countryId){
        if (null == casesByStates || casesByStates.size() <= 0 || null == map) {
            return;
        }
        cases = new ArrayList<>();
        fallbackEntities = new ArrayList<>();
        casesByStates.forEach(casesByState -> {
            String name = casesByState.getName();
            long provinceId = map.getOrDefault(name,0L);
            if (provinceId > 0) {
                Case aCase = new Case();
                aCase.setCountryId(countryId);
                aCase.setProvinceId(provinceId);
                aCase.setTotalCase(NumberUtil.parseInt(casesByState.getCasesReported()));
                aCase.setDate(DateUtil.stringToDate(lastUpdatedAtApify));// 使用接口时间
                cases.add(aCase);
            } else {// 没有省份id，记录错误信息
                FallbackEntity entity = getFallbackEntity(casesByState, lastUpdatedAtApify);
                fallbackEntities.add(entity);
            }

        });
        log.debug("日期:{},确诊数量:{}",lastUpdatedAtApify,cases.size());
    }

    /**
     * 错误信息实体
     * @param casesByState
     * @param date
     */
    @SneakyThrows
    private FallbackEntity getFallbackEntity(CasesByState casesByState, String date) {
        BeanMap beanMap = BeanMap.create(casesByState);
        beanMap.put("date",date);
        FallbackEntity entity = new FallbackEntity();
        entity.setType("allCaseInUS");
        entity.setTypeDesc("美国各州疫情信息");
        entity.setMessage(new ObjectMapper().writeValueAsString(beanMap));
        new ObjectMapper().writeValueAsString(entity);
        entity.insertIntoDb();
        return entity;
    }
}
