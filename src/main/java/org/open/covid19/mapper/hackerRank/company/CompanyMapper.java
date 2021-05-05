package org.open.covid19.mapper.hackerRank.company;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompanyMapper {
    int setList(List<Map<String, String>> list);
}
