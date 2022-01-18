package org.example.juc.check;

import java.util.concurrent.CountDownLatch;

/**
 * 数据库健康检查
 *
 * @author 王泓桥
 * @date 2021.12.27
 */
public class DatabaseHealthCheck extends HealthChecker{


    public DatabaseHealthCheck(CountDownLatch countDownLatch) {
        super(countDownLatch, "DatabaseHealthChecker");
    }

    @Override
    public void verifyService() {
        System.out.println("Checking" + this.getServerName());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("正在检查的" + this.getServerName() + "启动成功");
    }
}
