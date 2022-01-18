package org.example.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Condition唤醒机制
 *
 * @author 王泓桥
 * @date 2021.12.13
 */
public class ConditionNotify implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin - ConditionNotify");
        // 等价于synchronized(lock), 获得锁
        lock.lock();
        try {
            // 唤醒处于等待状态下的线程, 等价于Object.notify()
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("end - ConditionNotify");
        }
    }
}
