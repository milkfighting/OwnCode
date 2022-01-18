package org.example.juc;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.CountDownLatch;

/**
 * 计数器
 *
 * @author 王泓桥
 * @date 2021.12.24
 */
public class CountDownExample {

//    static CountDownLatch count = new CountDownLatch(3);

    static class ThreadOne extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("庄周" + "--执行完毕");
//            count.countDown();
        }
    }

    static class ThreadTwo extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("芒果" + "--执行完毕");
//            count.countDown();
        }
    }

    static class ThreadThree extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("蓝宝" + "--执行完毕");
//            count.countDown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        // 相当于main直接调用线程并在线程中调用countDown()
        CountDownLatch CDL = new CountDownLatch(3);
        ThreadUtil.execute(() -> {new ThreadOne().start(); CDL.countDown();});
        ThreadUtil.execute(() -> {new ThreadTwo().start(); CDL.countDown();});
        ThreadUtil.execute(() -> {new ThreadThree().start(); CDL.countDown();});

//        Thread t1 = new ThreadOne();
//        t1.start();
//        Thread t2 = new ThreadOne();
//        t2.start();
//        Thread t3 = new ThreadOne();
//        t3.start();

        // 上面的线程执行完后唤醒main线程, 即阻塞main线程
        CDL.await();

        // 主线程执行完毕后，程序并不会中止，因为子线程仍然存活，60s后程序终止；
        System.out.println("吉祥三宝！");
    }
}
