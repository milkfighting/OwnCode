package org.example.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * MapExample
 *
 * @author 王泓桥
 * @date 2021.12.13
 */
public class MapExample {

    public static void main(String[] args) {
        Map map = new HashMap();
//        iteratorBySet(map);
        iteratorKeyAndValue();
    }

    /**
     * set的方式遍历
     */
    private static void iteratorBySet(Map map) {
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            Object key = entry.getKey();
            entry.getValue();
            map.get(key);
        }
    }

    /**
     * map获取值
     */
    private static void iteratorKeyAndValue() {
        Map<String, String> map = new HashMap<>();
        map.put("芒果", "金渐层");
        map.put("蓝宝", "蓝金渐层");
        map.put("庄周", "蓝猫");
        map.put("牛奶", "哈士奇");
        map.put("柠檬", "萨摩耶");
        for (Map.Entry<String, String> stock : map.entrySet()) {
            System.out.println(stock.getKey() + stock.getValue());
        }
    }
}
