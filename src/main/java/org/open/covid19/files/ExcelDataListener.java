package org.open.covid19.files;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * easyExcel监听
 * @author wuchao
 */
public class ExcelDataListener<T> extends AnalysisEventListener<T> {
    private List<T> list;
    private ExcelCallback<T> callback;

    /**
     * 构造，传入回调函数
     * @param callback  回调
     */
    public ExcelDataListener(ExcelCallback<T> callback){
        list = new ArrayList<>();
        this.callback = callback;
    }

    /**
     * 每次读取成功都回调一次
     * @param data      一行数据
     * @param context   上下文
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        list.add(data);
    }

    /**
     * 全部读取成功后，回调此方法
     * @param context   上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        callback.success(list);
    }
}
