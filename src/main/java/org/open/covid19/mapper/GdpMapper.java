package org.open.covid19.mapper;

import org.open.covid19.entity.gdp.GdpEntity;

import java.util.List;

public interface GdpMapper {
    int batchInsertGdp(List<GdpEntity> list);
}
