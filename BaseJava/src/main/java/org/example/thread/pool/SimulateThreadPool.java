package org.example.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 模拟线程池
 *
 * @author 王泓桥
 * @date 2022.01.12
 */
public class SimulateThreadPool implements Runnable{

    // 需要线程池内线程共享
    private static BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        // 让线程实现复用的唯一办法, 就是线程不结束
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // 线程池中的线程获取阻塞队列中的任务, take()方法底层会加锁, 只有一个线程能获取
                Object task = queue.take();
                // 用start()就表示新建一个线程启动, 故还是用run()
                ((Runnable)task).run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 将任务添加到阻塞队列中
        queue.add(new Task());
    }

    static class Task implements Runnable{

        @Override
        public void run() {}
    }
}
