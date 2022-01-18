package org.example.io.impl;

import org.example.io.FileOperation;

import java.io.*;

/**
 * 文件操作实现类
 *
 * @author 王泓桥
 * @date 2021.09.13
 */
public class FileOperationImpl implements FileOperation {

    @Override
    public String read(String filePath) {
        // 创建读取文件需要的流
        try (FileInputStream inputStream = new FileInputStream(filePath);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String info = null;
            // 循环读取
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println(info);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("读取文件操作完毕");
        }
        return "读取成功";
    }

    @Override
    public void readNoBack(String filePath) {
        // 创建读取文件需要的流, 一般为文件/内存读取 -> 输入输出流 -> 缓存流
        try (FileInputStream inputStream = new FileInputStream(filePath);
             // 需指定编码格式, 防止出现中文字符乱码
             InputStreamReader reader = new InputStreamReader(inputStream, "GBK");
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String info = null;
            // 循环读取
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("读取文件操作完毕");
        }
    }

    @Override
    public void readByClassLoader(String filePath) {
        // 下面这个必须读取java本地项目中classPath路径下的文件, 即resources目录下
        try(InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)) {
            int info;
            // 循环读取
            while ((info = input.read()) != -1) {
                System.out.print(info + ", ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("读取完毕");
        }
    }
}
