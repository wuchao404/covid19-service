package org.open.covid19.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.open.covid19.api.Covid19Api;
import org.open.covid19.entity.Country;
import org.open.covid19.entity.CountryExcel;
import org.open.covid19.entity.jhu.ProvinceEntity;
import org.open.covid19.files.CountryExcelDateListener;
import org.open.covid19.utils.file.EasyExcelUtil;
import org.open.covid19.files.ExcelDataListener;
import org.open.covid19.mapper.CountriesMapper;
import org.open.covid19.service.ISetCountries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
@Service
public class SetCountriesImpl implements ISetCountries {
    @Autowired
    private CountriesMapper countriesMapper;
    @Autowired
    private Covid19Api covid19Api;

    @Override
    public boolean setAll() {
        List<Country> countries = covid19Api.getCountries();
        int resultLength = countriesMapper.setAll(countries);
        return countries.size() == resultLength;
    }

    @Override
    public void readCnNameFromExcel(File file) {
        CountryExcelDateListener listener = new CountryExcelDateListener(countriesMapper);
        ExcelReader reader = EasyExcel.read(file, CountryExcel.class, listener).build();
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        reader.read(sheet);
        reader.finish();
    }

    /**
     * excel，美国50个州
     * @param file
     */
    @Override
    public void readAmericanStatesFromExcel(File file) {
        ExcelDataListener<ProvinceEntity> listener = new ExcelDataListener<>((list) -> {
            if (null == list || list.size() <= 0){
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                ProvinceEntity provinceEntity = list.get(i);
                provinceEntity.setCountryId(15);// 美国
                provinceEntity.setProvinceId(15,i+1);
            }
            countriesMapper.batchInsertAmericanState(list);
        });
        EasyExcelUtil.readSheet(file, ProvinceEntity.class,listener,0);
    }


}
