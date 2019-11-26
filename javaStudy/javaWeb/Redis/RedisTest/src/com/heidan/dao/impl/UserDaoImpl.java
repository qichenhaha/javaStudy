package com.heidan.dao.impl;

import com.heidan.dao.IUserdao;
import com.heidan.pojo.User;
import com.heidan.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Create by heidan on 2019/11/26 21:21
 */

public class UserDaoImpl implements IUserdao {

    private JdbcTemplate  template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> listAll() {
        // 1.编写sql
        String sql  ="SELECT * FROM user";

        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);

        return template.query(sql, rowMapper);

    }
}
