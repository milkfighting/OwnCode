package org.example.thread.interrupt;

/**
 * 停止线程
 *
 * @author 王泓桥
 * @date 2021.09.29
 */
public class InterruptThread implements Runnable{

    /**
     * run方法外的interrupt()方法只是用于发送一个中断信号
     * run方法内的interrupt()方法才可以真正中断线程
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptThread());
        thread.start();
        Thread.sleep(5000);
        // 发送一个中断信号
        thread.interrupt();
        // 也是线程中断状态的复位, 与isInterrupted()一样
        Thread.interrupted();
    }

    @Override
    public void run() {
        // 线程无法自动结束的情况
//        try {
//            InterruptThread.class.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        while(true) {}
        // Thread.currentThread().isInterrupted()来获取线程中断状态(必须是实际运行的线程才能调用这个方法)
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // InterruptedException抛出后, 会恢复中断状态(复位), 中断标记恢复为false
                // 如果希望抛出异常后直接将线程中断, 再调用interrupt(), 这样的好处是可以在再次中断前保留一些临时数据
                // 此处是将中断状态变为true
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + "正在出发");
        }
    }

    /**
     * 其实就是维护了一个共享的复位标记来决定是否结束
     */
}
