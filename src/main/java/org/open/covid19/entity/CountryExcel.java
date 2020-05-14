package org.open.covid19.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * excel字段
 * @author wuchao
 */
@Data
public class CountryExcel {
    @ExcelProperty("abbr")
    private String iso2;

    @ExcelProperty("chinese")
    private String cnName;
}
