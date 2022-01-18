package org.example.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * 轻量级锁测试
 *
 * @author 王泓桥
 * @date 2021.10.08
 */
public class LightWeightLocj {
    public static void main(String[] args) {
        LightWeightLocj weight = new LightWeightLocj();
        // 打印对象内存布局
        System.out.println(ClassLayout.parseInstance(weight).toPrintable());
        // 加锁之后
        System.out.println("加锁之后");
        // 如果有其他线程进入到下面的同步块, 则先进行自旋
        synchronized (weight) {
            // main此时获得了偏向锁, 下一个线程过来后便自动升级为偏向锁
            System.out.println(ClassLayout.parseInstance(weight).toPrintable());
        }
        /**
         * 将偏向锁延迟设置为0：-XX:BiasedLockingStartupDelay=0
         * 在偏向锁状态下, 默认会有一些匿名对象获得偏向锁
         */
    }
}
