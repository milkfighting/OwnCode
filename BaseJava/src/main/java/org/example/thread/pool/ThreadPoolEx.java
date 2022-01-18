package org.example.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池示例
 *
 * @author 王泓桥
 * @date 2022.01.12
 */
public class ThreadPoolEx {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
    }
}
