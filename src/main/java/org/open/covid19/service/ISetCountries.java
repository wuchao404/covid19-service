package org.open.covid19.service;

import java.io.File;

public interface ISetCountries {
    /**
     * 全部国家列表
     * @return
     */
    boolean setAll();

    /**
     * 读取Excel
     */
    void readCnNameFromExcel(File file);
}
