package org.example.thread.status;

import java.util.concurrent.TimeUnit;

/**
 * 线程状态类
 *
 * @author 王泓桥
 * @date 2021.09.29
 */
public class ThreadStatus {

    /**
     * 测试线程状态步骤（重要）
     * a.debug这个项目, 生成target包
     * b.target -> classes -> status包（找到自己类所在的包）
     * c.右键status包, 选择Open in -> Terminal
     * d.jps查看当前java运行的线程, 找到与类名相同的线程号
     * e.jstack 线程号输出线程日志, 便可根据线程名查看状态
     */

    public static void main(String[] args) {
        // 模拟线程sleep()阻塞
        threadSleepStatus();
        // 模拟线程wait()阻塞
        threadWaitStatus();
        // 模拟线程锁阻塞
        new Thread(new BlockedDemo(), "BLOCKED-DEMO-01").start();
        new Thread(new BlockedDemo(), "BLOCKED-DEMO-02").start();

    }
    /**
     * 线程调用sleep()时的状态
     */
    private static void threadSleepStatus() {
        // 睡眠方法测试
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "TIME_WAITING").start();
    }

    /**
     * 线程调用wait()的状态
     */
    private static void threadWaitStatus() {
        // 等待方法测试
        new Thread(() -> {
            while (true) {
                // 定义一个全局锁, 即给整个类加锁
                synchronized (ThreadStatus.class) {
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "WAITING");
    }

    /**
     * 线程锁阻塞
     */
    static class BlockedDemo extends Thread {
        @Override
        public void run() {
            synchronized (BlockedDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
