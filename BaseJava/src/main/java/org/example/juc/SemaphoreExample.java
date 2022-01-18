package org.example.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯示例
 *
 * @author 王泓桥
 * @date 2021.12.29
 */
public class SemaphoreExample {

    public static void main(String[] args) {
        // 限制并发数
        Semaphore semaphore = new Semaphore(10);
        for (int i = 1; i <= 20; i++) {
            new Car(i, semaphore).start();
        }
    }

    static class Car extends Thread {
        private int num;
        private Semaphore semaphore;

        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获得一个令牌
                semaphore.acquire();
                System.out.println("第 " + num + " 辆车抢到一个车位");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("第 " + num + " 辆车开走了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放令牌
                semaphore.release();
            }
        }
    }
}
