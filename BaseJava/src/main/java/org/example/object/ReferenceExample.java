package org.example.object;

import java.lang.ref.WeakReference;

/**
 * 引用示例
 *
 * @author 王泓桥
 * @date 2022.01.05
 */
public class ReferenceExample {

    static Object object = new Object();

    public static void main(String[] args) {
        // 直接指向类级别对象, 就是强引用, 会阻止object的回收
//        Object strongRef=object;
        // 这里为弱引用, 这里的引用不会阻止object的回收
        WeakReference<Object> objectWeakReference = new WeakReference<>(object);
        object = null;
        System.gc();
        System.out.println(objectWeakReference.get());
        Thread thread = new Thread();
    }
}
