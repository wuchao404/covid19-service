package org.open.covid19.service;

import org.open.covid19.entity.Case;
import org.open.covid19.entity.Country;

import java.util.List;

public interface ICovid19ApiService {
    /**
     * 插入所有确诊数据
     */
    void insertAllCases();

    /**
     * 从某个日期之后，插入病例数据
     */
    void insertCasesFromDate();

}
