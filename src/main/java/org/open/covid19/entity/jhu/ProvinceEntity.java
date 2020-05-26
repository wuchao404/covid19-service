package org.open.covid19.entity.jhu;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 省份
 * @author wuchao
 */
@Data
public class ProvinceEntity {

    private long countryId;
    private long provinceId;
    /**
     * 简称
     */
    @ExcelProperty("abbr")
    private String abbr;
    /**
     * 州名
     */
    @ExcelProperty("provinceName")
    private String provinceName;
    /**
     * 中文名
     */
    @ExcelProperty("provinceCnName")
    private String provinceCnName;
    /**
     * 省会
     */
    private String capitalName;
    @ExcelProperty("capitalCnName")
    private String capitalCnName;

    /**
     * 省id为7位数字
     * @param code
     */
    public void setProvinceId(long countryId, int code) {
        this.provinceId = Long.parseLong(getPrefix(countryId) + getSuffix(code));
    }
    private String getPrefix(long code) {
        return code < 10 ? "100"+code : (code < 100 ? "10"+code : "1"+code);
    }
    private String getSuffix(int code) {
        return code < 10 ? "00"+code : (code < 100 ? "0"+code : ""+code);
    }

}
