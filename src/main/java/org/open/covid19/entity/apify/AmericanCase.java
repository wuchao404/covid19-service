package org.open.covid19.entity.apify;

import lombok.Data;
import org.open.covid19.entity.Case;
import org.open.covid19.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 美国各州确诊数据
 * @author wuchao
 */
@Data
public class AmericanCase {
    private String lastUpdatedAtApify;
    private String lastUpdatedAtSource;
    private String totalCases;
    private String totalDeaths;
    private List<CasesByState> casesByStates;
    /**
     * 转换后的实体
     */
    private List<Case> cases;

    @Data
    public class CasesByState{
        private Integer casesReported;
        private String communityTransmission;
        private String name;
        private String range;
    }

    public String getLastUpdatedAtApify() {
        return DateUtil.format(lastUpdatedAtApify,DateUtil.FORMAT_TZ, DateUtil.FORMAT_YYYY_MM_DD);
    }

    public String getLastUpdatedAtSource() {
        return DateUtil.format(lastUpdatedAtSource,DateUtil.FORMAT_TZ, DateUtil.FORMAT_YYYY_MM_DD);
    }

    /**
     * 转换实体
     * @param map key为省份名称，value为省份id
     * @return
     */
    public List<Case> cast2List(Map<String, Long> map,long countryId){
        if (null == casesByStates || casesByStates.size() <= 0 || null == map) {
            return null;
        }
        List<Case> cases = new ArrayList<>();
        casesByStates.forEach(casesByState -> {
            Case aCase = new Case();
            aCase.setCountryId(countryId);
            aCase.setProvinceId(map.get(casesByState.getName()));
            aCase.setTotalCase(casesByState.getCasesReported());
            aCase.setDate(DateUtil.stringToDate(lastUpdatedAtApify));// 使用接口时间
            cases.add(aCase);
        });
        return cases;
    }
}
