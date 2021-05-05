package test;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
       float num1 = 32.26f;
       float num2 = 32.16f;
       float num3 = 1f;
       float num4 = 0f;
       num4 = num1 * 100 - num2 * 100 - num3 * 100;
       new BigDecimal(32.26).divide(new BigDecimal(32.16));
        System.out.println(num1 * 100);
        System.out.println(num2 * 100);
        System.out.println(num3 * 100);
        System.out.println(num4 / 100);
    }




}
