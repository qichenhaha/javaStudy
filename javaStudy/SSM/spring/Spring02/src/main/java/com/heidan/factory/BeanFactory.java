package com.heidan.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Create by heidan on 2019/12/21 14:44
 */

public class BeanFactory {

    // 1.定义一个Properties对象
    private static Properties properties;

    // 定义一个map容器
    private static Map<String, Object> beans;

    // 使用静态代码块为Properties对象赋值
    static {
        properties = new Properties();
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            properties.load(resourceAsStream);
            beans = new HashMap<String, Object>();
            Enumeration keys = properties.keys();
            while (keys.hasMoreElements()) {
                // 取出每一个key
                String ks = keys.nextElement().toString();
                // 根据keys获取value
                String property = properties.getProperty(ks);
                // 反射创建对象
                Object value = Class.forName(property).newInstance();
                // 把key和value存入容器中
                beans.put(ks, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }

    /**
     * 根据bean的名称获取对象
     * @param beanName
     * @return
     */
    /*public static Object getBean(String beanName){
        Object bean = null;
        try {
            String beanPath = properties.getProperty(beanName);
            System.out.println("获取的路径：" + beanPath);
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }*/
}
