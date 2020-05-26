package org.open.covid19.utils.file;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.open.covid19.files.ExcelDataListener;

import java.io.File;

/**
 * EasyExcel读取工具
 * @author wuchao
 */
public class EasyExcelUtil {
    /**
     * 读取一个excel的sheet
     * @param file      excel文件
     * @param aClass    实体类class
     * @param listener  回调
     * @param sheetNo   sheet页码，从0开始
     */
    public static void readSheet(File file, Class aClass, ExcelDataListener listener, int sheetNo) {
        ExcelReader reader = EasyExcel.read(file, aClass, listener).build();
        ReadSheet sheet = EasyExcel.readSheet(sheetNo).build();
        reader.read(sheet);
        reader.finish();
    }
}
