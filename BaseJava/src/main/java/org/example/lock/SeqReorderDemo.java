package org.example.lock;

/**
 * CPU层面的指令重排序
 *
 * @author 王泓桥
 * @date 2021.11.30
 */
public class SeqReorderDemo {
    private static int x=0, y=0;
    private static int a=0, b=0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            x=0;y=0;
            a=0;b=0;
            Thread t1 = new Thread(() -> {
               a=1;
               x=b;
            });
            Thread t2 = new Thread(() -> {
               b=1;
               y=a;
            });
            t1.start();
            t2.start();
//            t1.join();
//            t2.join();
            /**
             * 可能结果
             * 1和1
             * 1和0
             * 0和1
             * 0和0: CPU中Store Buffer中的数据没有及时更新到CPU缓存行中所以会出现指令重排序问题
             * Store Buffer中与其他CPU中相同名称变量的值需要等到其他CPU中该变量失效后才会更新
             */
            String result = "第" + i + "次(" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
                continue;
            }
        }
    }
}
