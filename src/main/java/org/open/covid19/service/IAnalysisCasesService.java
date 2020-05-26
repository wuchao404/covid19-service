package org.open.covid19.service;

import java.util.List;
import java.util.Map;

/**
 * 分析确诊数据
 * @author wuchao
 */
public interface IAnalysisCasesService {
    /**
     * 将记录数量插入国家表中（每天为1个记录数）
     * @param
     * @return 插入失败的数据
     */
    List<Map<String, Integer>> updateRecordSize2Country();

    int updateRecordOptimizer();
    int updateBeginEnd();

    /**
     * 从某个日期后开始插入确诊数据
     */
    void insertRecordsFromDate();

    /**
     * 如果该国没有数据，则调用JHU接口，将确诊数据入库
     */
    void insertRecordsIfNoExist();
}
