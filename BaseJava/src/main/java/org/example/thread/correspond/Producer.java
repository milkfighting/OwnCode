package org.example.thread.correspond;

/**
 * 消费者
 *
 * @author 王泓桥
 * @date 2021.09.28
 */
public class Producer implements Runnable{

    private ShareResource resource = null;

    public Producer(ShareResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                resource.saveResource("芒果", "性感金渐层");
            } else {
                resource.saveResource("庄周", "憨憨大蓝猫");
            }
        }
    }
}
