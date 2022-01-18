package org.example.thread.threadlocal;

/**
 * ThreadLocalExample
 *
 * @author 王泓桥
 * @date 2021.12.29
 */
public class ThreadLocalExample {
    // 希望每一个线程获得的num都是0
//    private static int num=0;

    // 全局用户信息的存储
    static ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Thread[] thread=new Thread[5];
        for(int i = 0; i < 5; i++){
            thread[i]=new Thread(()->{
                int num = local.get();
                // 每个线程值得修改对其他线程不可见
                local.set(num+=5);
                System.out.println(Thread.currentThread().getName() + " " + local.get());
            });
        }
        local = null;
        for (int i = 0; i < 5; i++) {
            thread[i].start();
        }
    }

}
