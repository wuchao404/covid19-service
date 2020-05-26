package org.open.covid19.files;

import java.util.List;

/**
 * excel读写成功后的回调
 * @author wuchao
 * @param <T>
 */
public interface ExcelCallback<T> {
    /**
     * 成功回调
     * @param list  表格list
     */
    void success(List<T> list);
}
