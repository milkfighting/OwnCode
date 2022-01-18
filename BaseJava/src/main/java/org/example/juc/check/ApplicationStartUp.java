package org.example.juc.check;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 应用启动
 *
 * @author 王泓桥
 * @date 2021.12.27
 */
public class ApplicationStartUp {

    private static List<HealthChecker> servers;

    private static CountDownLatch count ;

    // 做成单例
    private static final ApplicationStartUp INSTANCE = new ApplicationStartUp();

    private ApplicationStartUp() {
    }

    public static ApplicationStartUp getINSTANCE() {
        return INSTANCE;
    }

    public static boolean checkExternalServer() throws InterruptedException {
        for (HealthChecker server : servers) {
            // 每个服务一个线程
            new Thread(server).start();
        }
        count.await();
        return true;
    }

    static {
        servers = new ArrayList<>();
        // 灵活配置CountDownLatch
        count = new CountDownLatch(2);
        servers.add(new CacheHealthChecker(count));
        servers.add(new DatabaseHealthCheck(count));
        // 放这里会导致主线程无法结束, 因为从上往下, 这时候回传一个空的countDown进去
//        count = new CountDownLatch(2);
    }
}
