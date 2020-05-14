package org.open.covid19.files;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.open.covid19.entity.CountryExcel;
import org.open.covid19.mapper.CountriesMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取Excel
 * @author wuchao
 */
@Slf4j
public class CountryExcelDateListener extends AnalysisEventListener<CountryExcel> {

    private CountriesMapper countryMapper;
    private List<CountryExcel> countryList;

    public CountryExcelDateListener(CountriesMapper countryMapper) {
        this.countryMapper = countryMapper;
        countryList = new ArrayList<>();
    }

    @Override
    public void invoke(CountryExcel country, AnalysisContext analysisContext) {
        log.debug("解析一条excel:{}",country.getCnName());
        countryList.add(country);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 保存
        countryMapper.updateCNName(countryList);
    }
}
