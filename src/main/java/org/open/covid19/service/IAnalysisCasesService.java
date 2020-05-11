package org.open.covid19.service;

import org.open.covid19.entity.Case;

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
}
