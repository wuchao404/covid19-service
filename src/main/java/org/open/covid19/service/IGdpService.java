package org.open.covid19.service;

import java.io.File;

public interface IGdpService {
    void batchInsertGdp();

    /**
     * 从excel中导入各省gdp
     */
    void importProvinceGdpFromExcel(File file);
}
