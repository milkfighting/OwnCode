package org.example.lock.serial;

import java.util.ArrayList;
import java.util.List;

/**
 * 串行线程测试类
 *
 * @author 王泓桥
 * @date 2022.02.07
 */
public class Test implements Runnable{

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            threads.add(new Thread(new Test()));
        }
        // 并发流
        threads.parallelStream().forEach(Thread::start);
        // 普通流, 普通流问题复现较容易
//        threads.stream().forEach(Thread::start);
    }

    @Override
    public void run() {
        System.out.println("开始");
        new ReentrantLockUtil().execute("" ,Thread.currentThread());
        System.out.println("结束");
    }
}
