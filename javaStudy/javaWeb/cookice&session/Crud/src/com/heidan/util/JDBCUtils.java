package com.heidan.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类 使用Duid连接池
 * Create by heidan on 2019/11/9 23:29
 */

public class JDBCUtils {

    private static DataSource ds;

    static {
        // 1.加载配置文件
        try {
            Properties pro = new Properties();
            //使用ClassLoader加载配置文件，获取字节流
            InputStream dr = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(dr);
            // 2.初始化连接池
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接池对象
     */

    public static DataSource getDataSource(){
        System.out.println(ds);
        return ds;
    }


    /**
     * 获取连接Connection对象
     */

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
