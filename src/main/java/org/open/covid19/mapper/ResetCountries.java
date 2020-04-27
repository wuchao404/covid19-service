package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.open.covid19.entity.Country;

import java.util.List;

/**
 * 将所有国家插入到数据库中
 * @author wuchao
 */
@Mapper
public interface ResetCountries {
    /**
     * 所有
     * @param countries
     */
    int setAll(List<Country> countries);
}
