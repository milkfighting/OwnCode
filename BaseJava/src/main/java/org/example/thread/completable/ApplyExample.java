package org.example.thread.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 有返回值的CompletableFuture
 *
 * @author 王泓桥
 * @date 2022.01.12
 */
public class ApplyExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf=CompletableFuture.supplyAsync(()->"Apply").thenApply(r->{
            return "r"+"Mic";
        });
        System.out.println(cf.get());

        CompletableFuture cf2=CompletableFuture.supplyAsync(()->"Combine")
                .thenCombineAsync(CompletableFuture.supplyAsync(()->"Message"),(r1,r2)->r1+r2);
        System.out.println(cf2.get());
    }
}
