package com.heidan.dao;

import com.heidan.domain.User;
import com.heidan.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Create by heidan on 2019/11/9 23:25
 * 操作数据库中user表中的类
 */

public class Userdao {

    // 声明JDBCTemplate对象共用
   private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登陆方法
     * @param user 只有用户名和密码
     * @return  user 包含用户的全部数据
     */
    public User login(User user){
        try {
            // 1.编写sql语句
            String sql = "select * from user where username = ? and password = ?";
            // 2. 调用query方法
            User user1 = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
            return user1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
