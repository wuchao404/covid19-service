package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.Country;

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
     * 插入记录数
     * @param countryId
     * @return
     */
    int updateRecordCountById(@Param("countryId") int countryId, @Param("count") int count);

    /**
     * 查询各个国家病例记录数
     * @return
     */
    List<Country> getAllRecords();

    /**
     * 批量更新到国家表中
     * @param countryList
     * @return
     */
    int updateAllRecords(List<Country> countryList);

    /**
     * 批量更新，使用begin-end
     * @param countryList
     * @return
     */
    int updateRecordWithBeginEnd(List<Country> countryList);

    List<Case> selectLessThanSizeRecords(int size);
}
