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

    /**
     * 读excel，获取美国50个州的信息
     * @param file
     */
    void readAmericanStatesFromExcel(File file);
}
