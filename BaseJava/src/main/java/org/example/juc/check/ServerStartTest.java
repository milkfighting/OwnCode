package org.example.juc.check;

/**
 * 服务启动测试
 *
 * @author 王泓桥
 * @date 2021.12.27
 */
public class ServerStartTest {
    public static void main(String[] args) {
        try {
            ApplicationStartUp.checkExternalServer();
        } catch (InterruptedException e) {
            // 自定义出现问题时的处理
        }
        System.out.println("服务启动成功");
    }
}
