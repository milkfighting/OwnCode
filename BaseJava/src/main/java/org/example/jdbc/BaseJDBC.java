package org.example.jdbc;

import java.sql.*;

/**
 * BaseJDBC
 *
 * @author 王泓桥
 * @date 2022.01.18
 */
public class BaseJDBC {

    public static void main(String[] args) {

    }

    private static void selectUser() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/mango";
        String username = "mango";
        String password = "966118";
        String sql = "Select * from user where username = ?";
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            // 建立与数据库的链接
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ":" + rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
