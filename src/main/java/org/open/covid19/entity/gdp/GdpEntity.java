package org.open.covid19.entity.gdp;

import lombok.Data;

/**
 * GDP
 * @author wuchao
 */
@Data
public class GdpEntity {
    private String countryEn;
    private String countryCn;
    private long gdp;
    private int year;
    private String continentEn;
    private String continentCn;
}
