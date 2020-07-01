package org.open.covid19.files.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.open.covid19.entity.gdp.GdpProvinceEntity;
import org.open.covid19.entity.gdp.ProvinceGdpExcel;
import org.open.covid19.mapper.GdpProvinceMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取Excel,中国各省gdp
 * @author wuchao
 */
@Slf4j
public class GdpProvinceExcelListener extends AnalysisEventListener<ProvinceGdpExcel> {

    private List<GdpProvinceEntity> gdpEntityList;
    private GdpProvinceMapper gdpMapper;

    public GdpProvinceExcelListener(GdpProvinceMapper mapper) {
        this.gdpMapper = mapper;
        this.gdpEntityList = new ArrayList<>();
    }

    @Override
    public void invoke(ProvinceGdpExcel gdpExcel, AnalysisContext analysisContext) {
        List<GdpProvinceEntity> list = gdpExcel.cast2List();
        gdpEntityList.addAll(list);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
