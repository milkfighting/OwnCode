package org.example.thread.correspond;

/**
 * 共享资源对象
 * 用于生产者和消费者之间进行生产消费活动的中间对象
 *
 * @author 王泓桥
 * @date 2021.09.28
 */
public class ShareResource {
    /** 姓名 */
    private String name;
    /** 性别 */
    private String sex;
    /** 对象状态 */
    private boolean isEmpty = true;

    /**
     * 生产者存储数据
     */
    synchronized public void  saveResource(String name, String sex) {
        try {
            // 如果对象为空, 那么必须等消费者进来消费完才能在进行生产
            // if只会确认一次, 而while会确认两次
            while (!isEmpty) {
                // 表示将当前线程放入等待池
                this.wait();
            }
            this.name = name;
            /**
             * 在姓名和性别之间加睡眠, 可能会出现刚赋值名字, thread-consumer便来取走这次循环的resource, 会出现性别为null的情况
             */
            Thread.sleep(10);
            this.sex = sex;
            // 表示生产完成
            isEmpty = false;
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费者消费数据
     */
    synchronized public void consumeResource() {
        try {
            // 如果生产者没有生产对象, 也需要等待
            while (isEmpty) {
                this.wait();
            }
            Thread.sleep(10);
            System.out.println(this.name + " - " + this.sex);
            isEmpty = true;
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
