package org.example.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 动态设置线程数量
 *
 * @author 王泓桥
 * @date 2022.01.13
 */
public class DynamicExample {
    // 自定义线程池
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 6, 60 , TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        printPoolStatus("before");
        pool.setCorePoolSize(4);
        pool.setMaximumPoolSize(8);
        printPoolStatus("after");
    }

    private static void printPoolStatus(String name) {
        System.out.println("核心线程数量：" + pool.getCorePoolSize() + ", 最大线程数量：" + pool.getMaximumPoolSize());
    }
}
