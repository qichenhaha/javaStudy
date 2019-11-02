package com.heidan.demo01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Create by heidan on 2019/10/24 12:41
 */

public class JDBC01 {

    public static void main(String[] args) throws Exception {
        // 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/person", "root", "root");
        // 定义sql语句
        String sql ="Select * form person";
        // 获取sql执行对象
        Statement statement = connection.createStatement();
        // 执行sql语句
        ResultSet resultSet = statement.executeQuery(sql);


    }
}
