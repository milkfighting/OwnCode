package org.example.juc.link;

/**
 * 打印进程
 *
 * @author 王泓桥
 * @date 2021.12.24
 */
public class PrintProcessor implements IRequestProcessor {

    protected IRequestProcessor nextProcessor;

    // 确认下一个链路节点是什么
    public PrintProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void processRequest(Request request) {
        // 执行完内容后在调用下一个链路节点
        nextProcessor.processRequest(request);
    }
}
