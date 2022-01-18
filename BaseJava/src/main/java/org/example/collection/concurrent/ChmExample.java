package org.example.collection.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

/**
 * ConcurrentHashMap示例
 *
 * @author 王泓桥
 * @date 2022.01.11
 */
public class ChmExample {

    private static final ConcurrentMap<String, Integer> USER_ACCESS_COUNT = new ConcurrentHashMap<>();

    public static void main(String[] args) {

    }

    /**
     * 简单的map
     */
    private static void simpleMap() {
        // 解决线程安全问题 synchronized (ChmExample.class)
        // 做用户访问次数记录
        Integer accessCount = USER_ACCESS_COUNT.get("mongo");
        // 这里多线程时候会有线程安全问题
        if (null == accessCount) {
            USER_ACCESS_COUNT.put("mongo", 1);
        } else {
            USER_ACCESS_COUNT.put("mongo", accessCount + 1);
        }
        // 上面操作可替换为 USER_ACCESS_COUNT.merge("mongo", 1, Integer::sum);
        System.out.println(USER_ACCESS_COUNT.get("mongo"));
    }

    /**
     * ConcurrentHashMap解决线程安全问题
     */
    private static void concurrentMapEx() {
        Integer accessCount = USER_ACCESS_COUNT.get("mongo");
        // 如果key不存在, 调用后面的mappingFunction计算
        USER_ACCESS_COUNT.computeIfAbsent("mongo", k -> 1);
        // 如果key存在, 调用后面的mappingFunction计算
        USER_ACCESS_COUNT.computeIfPresent("mongo", (k, v) -> v + 1);
    }

    /**
     * merge()
     */
    private static void mergeEx() {
        ConcurrentMap<Integer, Integer> map = new ConcurrentHashMap();
        Stream.of(1, 3, 4, 5, 32, 1).forEach(v -> {
            // 5表示每出现一次, value值+5
            map.merge(v, 5, Integer::sum);
        });
        System.out.println(map);
    }
}
