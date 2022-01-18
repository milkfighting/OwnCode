package org.example.cache;

import org.openjdk.jol.info.ClassLayout;

/**
 * 对象内存布局
 *
 * @author 王泓桥
 * @date 2021.10.08
 */
public class ObjectCache {
    /**
     * 压缩对象指针, 得是有空对象时才会进行
     */
    Object object = new Object();
    public static void main(String[] args) {
        ObjectCache cache = new ObjectCache();
        // 打印对象内存布局
        // 在JDK1.8之后会对Klass Pointer进行压缩, 可以在VM Options中用-XX:-UseCompressedOops, 注意冒号后的-代表关闭, +代表开启
        System.out.println(ClassLayout.parseInstance(cache).toPrintable());
    }
}
