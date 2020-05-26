package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.open.covid19.entity.Country;
import org.open.covid19.entity.CountryExcel;
import org.open.covid19.entity.jhu.ProvinceEntity;

import java.util.List;

/**
 * 国家表
 * @author wuchao
 */
@Mapper
public interface CountriesMapper {
    /**
     * 所有
     * @param countries
     */
    int setAll(List<Country> countries);

    /**
     * 国家列表
     * @return
     */
    List<Country> getAll();

    /**
     * 批量更新中文名称
     * @param countryList
     * @return
     */
    int updateCNName(List<CountryExcel> countryList);

    /**
     * 批量插入美国50个州
     * @param list
     * @return
     */
    int batchInsertAmericanState(List<ProvinceEntity> list);
}
