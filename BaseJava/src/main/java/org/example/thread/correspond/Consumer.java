package org.example.thread.correspond;

/**
 * 消费者
 *
 * @author 王泓桥
 * @date 2021.09.28
 */
public class Consumer implements Runnable{

    private ShareResource resource = null;

    public Consumer(ShareResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            resource.consumeResource();
        }
    }
}
