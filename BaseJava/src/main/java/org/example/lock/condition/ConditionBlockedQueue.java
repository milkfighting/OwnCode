package org.example.lock.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition阻塞队列
 *
 * @author 王泓桥
 * @date 2021.12.15
 */
public class ConditionBlockedQueue {

    // 表示阻塞队列中的容器
    private List<String> items;
    // 元素个数（表示已经添加的元素个数）
    private volatile int size;
    // 数组的容量
    private volatile int count;
    private Lock lock=new ReentrantLock();
    /**
     * 多个队列是与wait/notify最大的不同, 使不同线程的阻塞放在不同队列中, 可以精确唤醒要唤醒的线程
     * wait/notify只有一个线程, 所以唤醒的时候要去竞争, 会有不确定性
     */
    // 让take方法阻塞
    private final Condition notEmpty=lock.newCondition();
    // 放add方法阻塞
    private final Condition notFull=lock.newCondition();

    public ConditionBlockedQueue(int count){
        this.count=count;
        items=new ArrayList<>(count);
    }

    /**
     * 添加一个元素，并且阻塞添加
     *
     */
    public void put(String item) throws InterruptedException {
        lock.lock();
        try{
            if(size >= count){
                System.out.println("队列满了，需要先等一会");
                notFull.await();
            }
            // 增加元素个数
            ++size;
            items.add(item);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 取出一个元素, 阻塞取出
     */
    public String take() throws InterruptedException {
        lock.lock();
        try{
            if(size == 0){
                System.out.println("阻塞队列空了，先等一会");
                notEmpty.await();
            }
            // 取出元素
            --size;
            String item=items.remove(0);
            notFull.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionBlockedQueue cbqe=new ConditionBlockedQueue(10);
        // 生产者线程
        Thread t1=new Thread(()->{
            Random random=new Random();
            for (int i = 0; i < 1000; i++) {
                String item="item-"+i;
                try {
                    //如果队列满了，put会阻塞
                    cbqe.put(item);
                    System.out.println("生产一个元素："+item);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        // 保证生产了一个再进行取出
        Thread.sleep(100);
        // 消费者
        Thread t2=new Thread(()->{
            Random random=new Random();
            for (;;) {
                try {
                    // 取出元素
                    String item=cbqe.take();
                    System.out.println("消费者线程消费一个元素："+item);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }
}
