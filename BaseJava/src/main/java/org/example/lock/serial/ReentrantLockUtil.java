package org.example.lock.serial;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 串行锁类
 *
 * @author 王泓桥
 * @date 2022.02.07
 */
public class ReentrantLockUtil {

    Map<String, ReentrantLock> mutexCache = new ConcurrentHashMap<>();

    public void execute(String key, Runnable statement) {
        ReentrantLock mutexKey = mutexCache.computeIfAbsent(key, k -> new ReentrantLock());

        mutexKey.lock();
        /**
         * 要注意tomcat一次会放进一定量的请求，当第一个线程获得创建的锁的时候，同批进来的其他线程便会被synchronized阻塞,
         * 当第一个线程执行完后, 另一个同批线程获得锁, 此时如果下一批线程进来, 会创建新的锁, 这时候两批线程锁明显是不同的,
         * 就会出现并行的情况
         * synchronized (mutexKey) synchronized比较的是对象, 如果需要比较字符内容, 则用字符串.intern()将字符串放进常量池里面
         */

        try {
            statement.run();
        } finally {
            // 如果串行队列中已经没有线程了, 就将这把锁移除, 可以避免并行的发生
            // 但极端情况下会存在刚进if判断就有下一批线程进来获得将要remove的锁, 然后此时缓存中没有该锁, 然后下一个线程又创建新锁, 出现并发的问题
            if (mutexKey.getQueueLength() == 0) {
                mutexCache.remove(mutexKey);
            }
            mutexKey.unlock();
        }
    }

    /**
     * 解决上述问题
     */
    public void perfectExecute(String key, Runnable statement) {
        ReentrantLock mutexKey = null;
        ReentrantLock mutexKeyInCache;
        do {
            if (mutexKey != null) {
                mutexKey.unlock();
            }
            mutexKey = mutexCache.computeIfAbsent(key, k -> new ReentrantLock());
            mutexKey.lock();
            mutexKeyInCache = mutexCache.get(mutexKey);
            /**
             * 会出现问题的情况
             * 1. mutexCache == null;
             * 2. mutexKey != mutexKyeInCache
             */
        } while (mutexKeyInCache == null || mutexKey != mutexKeyInCache);
        try {
            statement.run();
        } finally {
            if (mutexKey.getQueueLength() == 0) {
                mutexCache.remove(mutexKey);
            }
            mutexKey.unlock();
        }
    }
}
