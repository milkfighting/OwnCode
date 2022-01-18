package org.example.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁详解
 *
 * @author 王泓桥
 * @date 2021.12.02
 */
public class LockExample {
    // 放在全局的话所有线程共享同一个实例, 此时为全局锁
    static Lock lock = new ReentrantLock();
    private static int count = 0;

    /**
     * 如果是非公平锁, 当线程t2被t1线程unlock()唤醒后, 再t2调用lock()方法的临界点, 此时tn刚好准备进来获取线程状态(此时t(n-1)为AQS队列队尾)
     * 这时如果允许tn抢占到锁时则此时是非公平锁
     */

    /**
     * 自增
     */
    private static void inc() {
        // 解决原子性问题, 未抢占到锁会阻塞
        lock.lock();
        // 没有抢占到锁不会阻塞
//        if (!lock.tryLock()) {
//            // 这里面可以写没有抢占到锁的逻辑
//            System.out.println("没有抢占到锁");
//        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁一定要在finally释放
            lock.unlock();
        }
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> LockExample.inc()).start();
            Thread.sleep(1000);
        }
        Thread.sleep(5000);
        // 注意控制睡眠时间, 保证上面创建线程都执行完再执行输出语句
        System.out.println("result = " + count);
    }
}
