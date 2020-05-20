package org.open.covid19.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 确诊
 * @author wuchao
 */
@Data
public class Case {
    @JsonProperty("Confirmed")
    private long totalCase;

    @JsonProperty("Recovered")
    private long totalRecovered;

    @JsonProperty("Deaths")
    private long totalDeath;

    @JsonProperty("Country")
    private String country;
    private String slug;

    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    private long countryId;

}
