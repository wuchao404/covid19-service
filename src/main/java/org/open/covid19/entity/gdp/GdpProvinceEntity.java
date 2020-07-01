package org.open.covid19.entity.gdp;

import lombok.Data;

import java.util.Date;

/**
 * 中国各省GDP
 */
@Data
public class GdpProvinceEntity {

    public GdpProvinceEntity(String province, Long gdp, Integer year) {
        this.province = province;
        this.gdp = gdp;
        this.year = year;
    }

    private Long id;

    private String province;

    private String abbr;

    private Long gdp;

    private Integer year;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private Boolean isDelete;

}