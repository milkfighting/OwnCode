package org.example.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏示例
 *
 * @author 王泓桥
 * @date 2021.12.29
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        int n = 5;
        // 可以在创建栅栏的时候设置回调内容, 在达到栅栏数限制时会触发
       CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
           System.out.println("所有线程执行完成, 继续处理其他问题");
       });
        for (int i = 0; i < n; i++) {
            new Writer(cyclicBarrier).start();
       }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "写入数据完毕, 等待其他线程执行");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
