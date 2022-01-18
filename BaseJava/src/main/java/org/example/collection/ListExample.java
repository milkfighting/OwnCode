package org.example.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * ListExample
 *
 * @author 王泓桥
 * @date 2021.12.21
 */
public class ListExample {
    public static void main(String[] args) {
        List<String> numList = new ArrayList<>();
        numList.add("1");
        numList.add("1");
        numList.add("猫");
        numList.add("5454");
        numList.add("2");
        System.out.println(numList);
    }
}
