package org.example.juc.link;

/**
 * 结尾进程
 *
 * @author 王泓桥
 * @date 2021.12.24
 */
public class FinalProcessor implements IRequestProcessor{


    @Override
    public void processRequest(Request request) {
        // 不需要下个节点, 此为最终节点
    }
}
