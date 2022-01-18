package org.example.juc.check;

import java.util.concurrent.CountDownLatch;

/**
 * 缓存健康检测
 *
 * @author 王泓桥
 * @date 2021.12.27
 */
public class CacheHealthChecker extends HealthChecker{

    public CacheHealthChecker(CountDownLatch countDownLatch) {
        super(countDownLatch, "CacheHealthChecker");
    }

    @Override
    public void verifyService() throws InterruptedException {
        System.out.println("Checking" + this.getServerName());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
//            e.printStackTrace();
            // 这种异常必须要捕获, 可以决定出现问题后自定义处理方式
            throw e;
        }
        System.out.println("正在检查的" + this.getServerName() + "启动成功");
    }
}
