package org.open.covid19.service;

import org.open.covid19.entity.Case;

import java.util.List;

public interface ICovid19Api {
    /**
     * 向某个国家插入确诊数据
     * @param iso2
     * @return
     */
    int insertCasesByIso2(String iso2);
}
