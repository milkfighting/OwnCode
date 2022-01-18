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
        iteratorBySet(map);
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
}
