package org.open.covid19.entity.gdp;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.open.covid19.utils.NumberUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 从Excel中导入中国各省gdp
 * @author wuchao
 */
@Data
public class ProvinceGdpExcel {
    @ExcelProperty("地区")
    private String province;
    @ExcelProperty("2019年")
    private String year2019;

    @ExcelProperty("2018年")
    private String year2018;

    @ExcelProperty("2017年")
    private String year2017;

    @ExcelProperty("2016年")
    private String year2016;

    @ExcelProperty("2015年")
    private String year2015;

    @ExcelProperty("2014年")
    private String year2014;

    @ExcelProperty("2013年")
    private String year2013;

    @ExcelProperty("2012年")
    private String year2012;

    @ExcelProperty("2011年")
    private String year2011;

    @ExcelProperty("2010年")
    private String year2010;

    /**
     * 将当前实体按年份逐个转换为新实体，生成一个数组
     * @return
     */
    public List<GdpProvinceEntity> cast2List() {
        List<GdpProvinceEntity> entityList = new ArrayList<>();
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2019),2019));
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2018),2018));
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2017),2017));
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2016),2016));
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2015),2015));
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2014),2014));
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2013),2013));
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2012),2012));
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2011),2011));
        entityList.add(new GdpProvinceEntity(province, NumberUtil.comma2Long(year2010),2010));
        return entityList;
    }
}
