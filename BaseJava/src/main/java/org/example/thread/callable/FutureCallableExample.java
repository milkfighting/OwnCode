package org.example.thread.callable;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 模拟线程池
 *
 * @author 王泓桥
 * @date 2022.01.12
 */
public class FutureCallableExample {
    static class CalculationCallable implements Callable<Integer> {
        private final int x;
        private final int y;

        public CalculationCallable(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public Integer call() throws Exception {
            System.out.println("begin call:" + new Date());
            TimeUnit.SECONDS.sleep(2); //模拟任务执行的耗时
            return x + y;
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CalculationCallable calculationCallable = new CalculationCallable(1,2);
        FutureTask<Integer> futureTask = new FutureTask<>(calculationCallable);
        //本质上还是一个线程.
        new Thread(futureTask).start();
        System.out.println("begin execute futuretask:" + new Date());
        // 阻塞方法-(join)
        Integer rs = futureTask.get();
        System.out.println("result:" + rs + "");
        System.out.println("end execute futuretask:" + new Date());
    }
}
