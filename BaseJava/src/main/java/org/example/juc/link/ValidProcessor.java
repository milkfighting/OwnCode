package org.example.juc.link;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 验证进程
 *
 * @author 王泓桥
 * @date 2021.12.24
 */
public class ValidProcessor extends Thread implements IRequestProcessor{

    protected IRequestProcessor nextProcessor;

    // 创建一个阻塞队列
    protected BlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    public ValidProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request);
        // 执行验证过程, 但如果要实时获得验证结果这样就不合理, 需调用线程改为异步处理
        nextProcessor.processRequest(request); // 获取实时结果这段就得注掉
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 异步来执行请求处理, 需要实时获得结果的都可以这样写
                Request take = requests.take();
                System.out.println("ValidProcessor: " + take);
                if (null != nextProcessor) {
                    nextProcessor.processRequest(take);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
