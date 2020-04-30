package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.Country;

import java.util.List;
@Mapper
public interface Covid19ApiMapper {
    /**
     * 设置某个国家的确诊数据（全部日期）
     * @param cases
     * @return
     */
    int setCasesList2country(@Param("countryId") long countryId,@Param("cases") List<Case> cases);

    /**
     * 根据简称查询id
     * @param iso2
     * @return
     */
    long selectCountryId(String iso2);
    /**
     * 国家列表
     * @return
     */
    List<Country> getCountryList();
}
