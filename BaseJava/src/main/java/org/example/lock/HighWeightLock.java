package org.example.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 重量级锁实现
 *
 * @author 王泓桥
 * @date 2021.10.08
 */
public class HighWeightLock {
    public static void main(String[] args) {
        HighWeightLock weight = new HighWeightLock();
        Thread t1 = new Thread(() -> {
            synchronized (weight) {
                System.out.println("t1 Locked");
                System.out.println(ClassLayout.parseInstance(weight).toPrintable());
            }
        });
        t1.start();
        // main与t1竞争同一实例
        synchronized (weight) {
            System.out.println("main Locked");
            System.out.println(ClassLayout.parseInstance(weight).toPrintable());
        }
    }
}
