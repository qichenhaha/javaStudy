package com.heidan.jdbc;

import java.sql.*;

/**
 * Create by heidan on 2019/12/21 12:16
 * 程序的耦合:
 * 耦合：程序间的依赖关系
 * 包括：
 * 类之间的依赖
 * 方法之间的依赖
 * 解耦: 降低程序间的依赖关系
 * 实际开发中:
 * 应该做到：编译器不依赖，运行时才依赖
 * 解耦的思路:
 * 第一步：使用反射来创建对象，而避免new关键字
 * 第二步: 通过读取配置文件获取要创建的对象全类名
 */

public class demo01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 1.注册驱动
        // 在没有依赖的情况下，这样是无法编译的，独立性非常差
        //  DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
        // 2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy", "root", "root");
        // 3.获取操作数据库的预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement("select  * from account");
        // 4.执行sql，得到结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        // 5.遍历结果集
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("money"));
        }
        // 6.释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
