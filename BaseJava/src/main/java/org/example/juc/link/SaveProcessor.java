package org.example.juc.link;

/**
 * 存储进程
 *
 * @author 王泓桥
 * @date 2021.12.24
 */
public class SaveProcessor implements IRequestProcessor {

    protected IRequestProcessor nextProcessor;

    public SaveProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void processRequest(Request request) {
        // 执行完内容后在调用下一个链路节点
        nextProcessor.processRequest(request);
    }
}
