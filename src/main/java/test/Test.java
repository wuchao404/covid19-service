package test;

import org.open.covid19.utils.DateUtil;

public class Test {
    public static void main(String[] args) {
        int days = DateUtil.howManyDaysFromNow(DateUtil.stringToDate("2020-05-18"));
        System.out.println("距离天数：" + days);
    }
}
