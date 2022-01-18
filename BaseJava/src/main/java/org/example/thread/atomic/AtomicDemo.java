package org.example.thread.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 原子性验证类
 *
 * @author 王泓桥
 * @date 2021.10.08
 */
public class AtomicDemo {

    int i = 0;

    /**
     * 自增（会出现原子性问题）
     */
    public void increment() {
        // 输出结果不符合预期是因为i++不是原子性操作
        i++;
    }

    /**
     * 自增（不会出现原子性问题）
     */
    public synchronized void incrementSynchronized() {
        i++;
    }

    /**
     * 自增（不会出现原子性问题）
     */
    public void incrementLock() {
        Lock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
     AtomicDemo demo = new AtomicDemo();
     Thread[] threads = new Thread[2];
     for (int i = 0; i < 2 ; i++) {
         threads[i] = new Thread(() -> {
             for (int j = 0; j < 10000 ; j++) {
//                 demo.increment();
                 demo.incrementLock();
             }
         });
         threads[i].start();
     }
     // join()保证子线程执行完之后再执行main方法, 及输出语句必在子线程执行完之后
     threads[0].join();
     threads[1].join();
     System.out.println("i结果为" + demo.i);
    }
}
