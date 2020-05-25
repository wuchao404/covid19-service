package test;

import org.open.covid19.utils.DateUtil;

public class Test {
    public static void main(String[] args) {
        String format = DateUtil.format("5/13/21", "M/d/yy", "yyyy-MM-dd");
        System.out.println("format:" + format);
    }
}
