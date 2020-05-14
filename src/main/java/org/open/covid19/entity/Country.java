package org.open.covid19.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 国家
 * @author wuchao
 */
@Data
public class Country {
    @JsonIgnore
    private Integer id = 0;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Slug")
    private String slug;

    @JsonProperty("ISO2")
    @ExcelProperty("abbr")
    private String iso2;

    private int recordCount;

    @ExcelProperty("chinese")
    private String cnName;
}
