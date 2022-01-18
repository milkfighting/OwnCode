package org.example.lock;

import java.util.concurrent.TimeUnit;

/**
 * JoinExample
 *
 * @author 王泓桥
 * @date 2021.12.01
 */
public class JoinExample extends Thread {

    private static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        JoinExample je = new JoinExample();
        je.start();
        // main线程需等到je线程执行结束, 未结束则处于阻塞状态, 确保je线程执行结束
        je.join();
        if (x == 100) {
            System.out.println("main线程执行结束");
        }
        // 当run方法执行结束, 会给一个notify的信号, 在c++底层会调用一个lock.notify_all(thread), 将被join方法阻塞的所有线程唤醒
    }

    @Override
    public void run() {
        try {
            x = 100;
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
