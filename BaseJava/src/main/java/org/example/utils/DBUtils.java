package org.example.utils;

import java.sql.*;
import java.util.Properties;

/**
 * 数据库工具类
 *
 * @author 王泓桥
 * @date 2021.09.14
 */
public class DBUtils {

    private static Properties properties = new Properties();

    /**
     * 将不会出现变化的静态资源放到静态代码块中去存放
     */
    static {
        try {
            // 加载配置文件
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
            // 加载驱动
            Class.forName(properties.getProperty("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立连接
     */
    public static Connection getConnection() {
        try {
            // 与db.properties中属性名一致
            return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭对应进程 Connection, PreparedStatement, ResultSet
     */
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        if (null != connection) {
            connection.close();
        }
        if (null != statement) {
            statement.close();
        }
        if (null != resultSet) {
            resultSet.close();
        }
    }
}
