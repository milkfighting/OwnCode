package org.example.process;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 进程类
 *
 * @author 王泓桥
 * @date 2021.09.22
 */
public class ProcessDemo {
    public static void main(String[] args) {
        execProcessByRuntime();
    }

    /**
     * Runtime执行进程
     */
    private static void execProcessByRuntime() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("D:\\Notepad++\\notepad++.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ProcessBuilder执行进程
     */
    private static void execProcessByProcessBuilder() {
        ProcessBuilder builder = new ProcessBuilder("D:\\Notepad++\\notepad++.exe");
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
