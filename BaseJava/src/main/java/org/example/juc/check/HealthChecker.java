package org.example.juc.check;

import java.util.concurrent.CountDownLatch;

/**
 * 健康检测
 *
 * @author 王泓桥
 * @date 2021.12.24
 */
public abstract class HealthChecker implements Runnable{

    // 服务名称
    private String serverName;

    // 服务启动状态
    private boolean serverStatus;

    private  CountDownLatch countDownLatch;

    public HealthChecker(CountDownLatch countDownLatch, String serverName) {
        this.serverName = serverName;
        this.countDownLatch = countDownLatch;
    }

    /**
     * 检查服务健康状态
     */
    public abstract void verifyService() throws InterruptedException;

    @Override
    public void run() {
        try {
            verifyService();
            serverStatus = true;
        } catch (Exception e) {
            serverStatus = false;
        } finally {
            if (countDownLatch != null) {
                // 释放
                countDownLatch.countDown();
            }
        }
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public boolean isServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(boolean serverStatus) {
        this.serverStatus = serverStatus;
    }
}
