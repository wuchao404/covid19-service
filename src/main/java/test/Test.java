package test;

import org.open.covid19.utils.DateUtil;

public class Test {
    public static void main(String[] args) {
        String local2tz = DateUtil.local2tz("2020-09-29");
        System.out.println(local2tz);
    }
}
