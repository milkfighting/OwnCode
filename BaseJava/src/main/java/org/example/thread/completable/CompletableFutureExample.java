package org.example.thread.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 模拟线程池
 *
 * @author 王泓桥
 * @date 2022.01.12
 */
public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* CompletableFuture cf=CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ":异步执行一个任务");
        });

        //通过阻塞获取执行结果.
        System.out.println(cf.get());

        CompletableFuture cf1 = CompletableFuture.supplyAsync(() ->  "Hello World");
        cf1.thenAccept(rs -> {
            System.out.println(rs);
        });*/

        // () 表示形参，
        CompletableFuture cf1 = CompletableFuture.supplyAsync(() -> "Hello World").thenAccept(xx -> {
            System.out.println(xx);
        });
        //继续做其他事情。
    }
}
