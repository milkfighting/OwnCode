package org.example.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Condition等待机制
 *
 * @author 王泓桥
 * @date 2021.12.13
 */
public class ConditionWait implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin - ConditionWait");
        // 获得锁
        lock.lock();
        try {
            // 让当前线程阻塞, 等价于object.wait()
            condition.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("end - ConditionWait");
        }
    }
}
