package org.open.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.open.covid19.entity.gdp.GdpProvinceEntity;

import java.util.List;

@Mapper
public interface GdpProvinceMapper {
    GdpProvinceEntity selectByPrimaryKey(Long id);

    /**
     * 批量插入中国各省gdp，2010年-2019年
     * @param list
     * @return
     */
    int batchInsertList(List<GdpProvinceEntity> list);
}