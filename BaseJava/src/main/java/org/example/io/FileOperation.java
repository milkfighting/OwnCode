package org.example.io;

/**
 * 文件操作接口
 *
 * @author 王泓桥
 * @date 2021.09.13
 */
public interface FileOperation {

    /**
     * 读取文件内容
     *
     * @author 王泓桥
     * @date 2021.09.13
     */
    String read(String filePath);

    /**
     * 读取文件内容（无返回值）
     *
     * @author 王泓桥
     * @date 2021.09.14
     */
    void readNoBack(String filePath);

    /**
     * 通过类加载器读取资源
     *
     * @author 王泓桥
     * @date 2021.09.14
     */
    void readByClassLoader(String filePath);
}
