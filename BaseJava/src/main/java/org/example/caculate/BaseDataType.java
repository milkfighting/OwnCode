package org.example.caculate;

/**
 * BaseDataType
 *
 * @author 王泓桥
 * @date 2021.12.10
 */
public class BaseDataType {

    public static void main(String[] args) {
        double2Long();
    }

    private static void double2Long() {
        Long l1 = 100l;
        Double d1 = 200.10;
        System.out.println(d1/l1);
    }
}
