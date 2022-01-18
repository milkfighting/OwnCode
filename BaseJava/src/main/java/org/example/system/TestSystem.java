package org.example.system;

import org.example.io.FileOperation;
import org.example.io.impl.FileOperationImpl;

import java.io.File;

/**
 * System相关方法测试
 *
 * @author 王泓桥
 * @date 2021.09.13
 */
public class TestSystem {

    private static String filePath = "D:\\OwnCode";
    private static String fileName = "brother.txt";

    public static void main(String[] args) {
        TestSystem testSystem = new TestSystem();
        // 获取默认文件系统的分隔符
//        testSystem.getSeparator("file.separator");
        // 读取文件中内容
        FileOperation fileOperation = new FileOperationImpl();
//        System.out.println(fileOperation.read(testSystem.adaptOperatingSystem()));
//        fileOperation.readNoBack(testSystem.adaptOperatingSystem());
        fileOperation.readByClassLoader("brother.txt");
    }

    /**
     * 分隔符相关
     */
    private void getSeparator(String property) {
        // getProperty中的值有多个, 都是从系统中直接获取对应参数, 主要是为了适应不同操作系统
        String separator = System.getProperty(property);
        System.out.println(separator);
        System.out.println(File.separator);
    }

    /**
     * 将路径格式改为多系统适用
     */
    private String  adaptOperatingSystem() {
        String separator = File.separator;
        // 注意在java中一个右斜线代表转义, 所以此时\\才代表\
        if ("\\".equals(separator)) {
            // Windows系统时
            separator = separator + separator;
        } else if ("/" .equals(separator)) {
            // Linux系统时, 将存放配置文件的路径替换
            filePath = filePath.replace('\\', '/');
        }
        // 返回具体路径
        return filePath + separator + fileName;
    }
}
