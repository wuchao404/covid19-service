package org.open.covid19.utils;

/**
 * 数值类型处理
 * @author wuchao
 */
public class NumberUtil {
    /**
     * 字符串转为数字，出现异常则返回0
     * @param num 默认值：0
     * @return
     */
    public static Integer parseInt(String num){
        int number = 0;
        try {
            number = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }
}
