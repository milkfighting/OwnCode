package org.example.thread.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 模拟线程池
 *
 * @author 王泓桥
 * @date 2022.01.12
 */
public class RunExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cxf = CompletableFuture.supplyAsync(() -> "Both")
                .runAfterBoth(CompletableFuture.supplyAsync(() -> "Message"),() -> {
                    System.out.println("Done");
                });
        System.out.println(cxf.get());
    }
}
