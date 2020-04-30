package org.open.covid19.service;

import org.open.covid19.entity.Case;
import org.open.covid19.entity.Country;

import java.util.List;

public interface ICovid19ApiService {
    /**
     * 向某个国家插入确诊数据
     * @param iso2
     * @return
     */
    int insertCasesByIso2(String iso2);

    /**
     * 插入所有确诊数据
     */
    void insertAllCases();

}
