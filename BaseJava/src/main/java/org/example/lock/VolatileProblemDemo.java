package org.example.lock;

/**
 * volatile关键字问题
 *
 * @author 王泓桥
 * @date 2021.10.09
 */
public class VolatileProblemDemo {

    public static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        // 可见性问题
        visibility();
        System.out.println("====== 可见性问题线程启动 ======");
        Thread.sleep(1000);
        // 此时由于main线程中变量修改对于t1是不可见的, 故运行无法结束
        stop = true;
    }

    /**
     * 可见性问题示例
     */
    public static void visibility() {
        new Thread(() -> {
           int i = 0;
           // 此处通过JVM的深度编译JIT将这里替换成常量true, 所以后面的修改不生效(活性失效)
            /**
             * (1)可以通过volatile添加到变量上取消这种优化
             * (2)在VM options中添加 -Djava.compiler=NONE取消优化
             * (3)在while代码块内添加Thread.sleep(0), 使CPU发生切换, 值便会重新加载一次
             * (4)在while代码块内添加输出语句, 触发IO操作, 里面会涉及到synchronized同步锁, 对值会有影响
             */
           while (!stop) {
               i++;
               System.out.println("起飞");
           }
        }).start();
    }
}
