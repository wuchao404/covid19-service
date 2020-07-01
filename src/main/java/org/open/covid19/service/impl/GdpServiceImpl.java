package org.open.covid19.service.impl;

import org.open.covid19.entity.gdp.GdpProvinceEntity;
import org.open.covid19.entity.gdp.ProvinceGdpExcel;
import org.open.covid19.files.ExcelDataListener;
import org.open.covid19.mapper.GdpProvinceMapper;
import org.open.covid19.service.IGdpService;
import org.open.covid19.utils.file.EasyExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class GdpServiceImpl implements IGdpService {
    @Autowired
    GdpProvinceMapper gdpProvinceMapper;

    @Override
    public void batchInsertGdp() {
        // 读取excel

    }

    @Override
    public void importProvinceGdpFromExcel(File file) {
        ExcelDataListener<ProvinceGdpExcel> dataListener = new ExcelDataListener<>(list -> {
            if (null != list && list.size() > 0){
                List<GdpProvinceEntity> entityList = new ArrayList<>();
                // 读取excel后以年份为单位转换为新实体
                list.forEach(gdpExcel -> {
                    List<GdpProvinceEntity> childList = gdpExcel.cast2List();
                    entityList.addAll(childList);
                });
                if (entityList.size() > 0){
                    // 入库
                    gdpProvinceMapper.batchInsertList(entityList);
                }
            }
        });
        // 读取Excel
        EasyExcelUtil.readSheet(file, ProvinceGdpExcel.class,dataListener,0);
    }
}
