package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.open.covid19.entity.Case;

import java.util.List;

@Mapper
public interface AnalysisCaseMapper {
    /**
     * 查询某个国家的记录详情的数量(详情以天为单位)
     * @param countryId
     * @return
     */
    int getCasesSizeByCountry(int countryId);

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
