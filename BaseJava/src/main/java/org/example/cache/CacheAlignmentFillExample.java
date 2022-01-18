package org.example.cache;

import sun.misc.Contended;

/**
 * cpu内存对齐填充实例
 *
 * @author 王泓桥
 * @date 2021.11.29
 */
public class CacheAlignmentFillExample implements Runnable{
    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;
    private static ValuePadding[] longs;

    public CacheAlignmentFillExample(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 10; i++) {
            System.gc();
            final long start = System.currentTimeMillis();
            runTest(i);
            System.out.println(i + "Threads, duration = " + (System.currentTimeMillis()) + start);
        }
    }

    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValuePadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {

        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new CacheAlignmentFillExample(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }

    /**
     * 有对其填充的静态内部类
     */
    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }

    /**
     * 无对其填充的静态内部类
     */
    @Contended // 加上这个便可以实现对其填充
    protected final static class ValueNoPadding {
        // 8字节
        protected volatile long value = 0L;
    }
}
