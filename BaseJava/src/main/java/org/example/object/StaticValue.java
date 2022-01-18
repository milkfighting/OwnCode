package org.example.object;

/**
 * 静态赋值
 *
 * @author 王泓桥
 * @date 2021.11.30
 */
public class StaticValue {

    private static String name = "";

    public StaticValue(String name) {
        this.name = name;
    }

    public void printValue() {
        System.out.println(name);
    }

    public static void main(String[] args) {
        int i = 1899999999 + 1;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE - i);
    }
}

