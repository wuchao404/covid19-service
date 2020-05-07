package org.open.covid19.service;

import org.open.covid19.entity.Case;

import java.util.List;

/**
 * 分析确诊数据
 * @author wuchao
 */
public interface IAnalysisCasesService {
    /**
     * 查询某个国家的记录详情的数量(详情以天为单位)
     * @param iso2
     * @return
     */
    int getCasesSizeByCountry(String iso2);

    /**
     * 查询某个国家的病例总数
     * @param countryId
     * @return
     */
    long getTotalCaseByCountryId(int countryId);

    /**
     * 将总病例数插入到数据库
     * @param cases
     * @return
     */
    int insertAllTotalCase(List<Case> cases);
}
