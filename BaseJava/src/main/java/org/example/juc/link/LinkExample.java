package org.example.juc.link;

/**
 * 链
 *
 * @author 王泓桥
 * @date 2021.12.24
 */
public class LinkExample {

    public static void main(String[] args) {
        FinalProcessor finalProcessor = new FinalProcessor();
        SaveProcessor saveProcessor = new SaveProcessor(finalProcessor);
        PrintProcessor printProcessor = new PrintProcessor(saveProcessor);
        ValidProcessor validProcessor = new ValidProcessor(printProcessor);
        validProcessor.start();

        Request request = new Request();
        request.setName("芒果");
        validProcessor.processRequest(request);


    }
}
