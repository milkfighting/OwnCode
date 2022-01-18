package org.example.juc;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列
 *
 * @author 王泓桥
 * @date 2021.12.16
 */
public class DelayQueueTask implements Delayed {

    private String orderId;
    // 当前系统时间
    private long start=System.currentTimeMillis();
    // 允许延迟执行的时间
    private long time; //

    private static DelayQueue<DelayQueueTask> delayQueue =new DelayQueue();

    public DelayQueueTask(String orderId, long time){
        this.orderId=orderId;
        this.time=time;
    }

    public static void main(String[] args) {
        delayQueue.offer(new DelayQueueTask("1001",1000));
        delayQueue.offer(new DelayQueueTask("1002",5000));
        delayQueue.offer(new DelayQueueTask("1003",3000));
        delayQueue.offer(new DelayQueueTask("1004",6000));
        delayQueue.offer(new DelayQueueTask("1005",2000));
        delayQueue.offer(new DelayQueueTask("1006",8000));
        delayQueue.offer(new DelayQueueTask("1007",3000));
        while(true){
            try {
                DelayQueueTask task = delayQueue.take();
                System.out.println(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下一次执行的时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        // 延迟执行的差额
        return unit.convert((start + time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 对任务进行比较排序
     */
    @Override
    public int compareTo(Delayed o) {
        return (int)(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "DelayQueueExampleTask{" +
                "orderId='" + orderId + '\'' +
                ", start=" + start +
                ", time=" + time +
                '}';
    }
}
