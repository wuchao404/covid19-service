package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.open.covid19.entity.Case;
import org.open.covid19.entity.Country;
import org.open.covid19.entity.jhu.ProvinceEntity;

import java.util.List;
@Mapper
public interface Covid19ApiMapper {
    /**
     * 设置某个国家的确诊数据
     * @param cases
     * @return
     */
    int setCasesList2country(@Param("countryId") long countryId,@Param("cases") List<Case> cases);

    /**
     * 根据简称查询id
     * @param slug
     * @return
     */
    long selectCountryId(String slug);
    /**
     * 国家列表
     * @return
     */
    List<Country> getCountryList();

    /**
     * 全球病例总数（确诊、治愈、死亡）
     * @return
     */
    long getGlobalCaseCount();

    /**
     * 查询省份id
     * @param countryId     国家id
     * @param provinceName  省份名称
     * @return
     */
    long selectProvinceIdByName(@Param("countryId") long countryId, @Param("provinceName") String provinceName);

    /**
     * 查询美国所有州
     * @return
     */
    List<ProvinceEntity> selectUsStates();
}
