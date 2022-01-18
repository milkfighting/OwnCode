package org.example.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConditionExample
 *
 * @author 王泓桥
 * @date 2021.12.13
 */
public class ConditionExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        // 用同一个lock, 保证会存在竞争关系, 故A释放锁之后B才可以拿到所去唤醒A
        ConditionWait conditionWait = new ConditionWait(lock, condition);
        ConditionNotify conditionNotify = new ConditionNotify(lock, condition);
        new Thread(conditionWait).start();
        new Thread(conditionNotify).start();
    }

    /**
     * 直接使用condition
     */
    private void lockAndCondition() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            lock.newCondition().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        lock.newCondition().signal();
    }
}
