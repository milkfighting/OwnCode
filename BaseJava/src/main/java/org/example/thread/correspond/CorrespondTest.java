package org.example.thread.correspond;

/**
 * 线程通信测试类
 *
 * @author 王泓桥
 * @date 2021.09.28
 */
public class CorrespondTest {

    public static void main(String[] args) {
        ShareResource resource = new ShareResource();
        // 启动生产者线程
        new Thread(new Producer(resource)).start();
        // 启动消费者线程
        new Thread(new Consumer(resource)).start();
    }
}
