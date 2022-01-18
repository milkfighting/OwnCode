package org.example.fork;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 进程类
 *
 * @author 王泓桥
 * @date 2021.09.22
 */
public class ForkJoinExample {

    //java8 parallStream

    // 针对一个数字，做计算。
    private static final Integer MAX = 200;

    // 定义一个任务
    static class CalcForJoinTask extends RecursiveTask<Integer> {
        // 子任务的开始计算的值
        private Integer startValue;
        // 子任务结束计算的值
        private Integer endValue;

        public CalcForJoinTask(Integer startValue, Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }
        @Override
        protected Integer compute() {
            // 如果当前的数据区间已经小于MAX了，那么接下来的计算不需要做拆分
            if(endValue - startValue < MAX){
                System.out.println("开始计算：startValue:" + startValue + " ; endValue:" + endValue);
                Integer totalValue=0;
                for(int i = this.startValue; i <= this.endValue; i++){
                    totalValue += i;
                }
                return totalValue;
            }
            // 定义一个新区间(上半区) 1--5000 下一次就是1--2500 2501-5000
            CalcForJoinTask task1 = new CalcForJoinTask(startValue, (startValue+endValue)/2);
            task1.fork();
            // 定义一个新区间(下半区) 5001--10000
            CalcForJoinTask task2 = new CalcForJoinTask((startValue+endValue)/2+1, endValue);
            task2.fork();
            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) {
        CalcForJoinTask calcForJoinTask = new CalcForJoinTask(1, 10000);
        ForkJoinPool pool = new ForkJoinPool();
        // 提交任务并返回计算结果
        ForkJoinTask<Integer> taskFuture = pool.submit(calcForJoinTask);
        try {
            // 获得执行结果
            Integer result = taskFuture.get();
            System.out.println("result: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
