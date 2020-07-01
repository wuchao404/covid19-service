package org.open.covid19.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数值类型处理
 * @author wuchao
 */
public class NumberUtil {

    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");

    /**
     * 带逗号的数字转换为long类型
     * @param content
     * @return
     */
    public static Long comma2Long(String content) {
        if (null == content || "".equals(content)){
            return 0L;
        }
        Matcher matcher = NUMBER_PATTERN.matcher(content.replaceAll(",", ""));
        return matcher.find() ? Long.parseLong(matcher.group()) : 0L;
    }

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
//            e.printStackTrace();
        }
        return number;
    }
}
