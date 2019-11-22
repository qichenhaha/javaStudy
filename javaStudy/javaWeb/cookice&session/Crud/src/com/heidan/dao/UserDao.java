package com.heidan.dao;

import com.heidan.domain.User;
import com.heidan.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Types;
import java.util.List;

/**
 * Create by heidan on 2019/11/20 20:49
 */

public class UserDao {

    // 声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    // 查询所有用户信息
    public List<User> listAll(){
        // 1.编写sql
        String sql  ="SELECT * FROM user";

        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);

        return template.query(sql, rowMapper);
    }

    // 根据id查询所有用户信息
    public User UserById(int id){
        String sql = "SELECT * FROM user WHERE id = ?";
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
        return template.queryForObject(sql,rowMapper,id);
    }


    // 新增
    public int UserAdd(User user){
        System.out.println("user：" + user);
        String sql ="INSERT INTO user (name,gender,age,address,email) VALUES (?,?,?,?,?)";

        Object[] params = new Object[]{user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getEmail()};

        int types[] = new int[]{Types.VARCHAR,Types.CHAR, Types.INTEGER,Types.VARCHAR,Types.VARCHAR};

        return template.update(sql, params,types);
    }


    // 修改
    public int UserUpdate(User user){
        String sql ="UPDATE `user` SET name=?,gender=?,age=?,address=?,email=? WHERE id = ?";
        Object[] params = new Object[]{user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getEmail(),user.getId()};
        int types[] = new int[]{Types.VARCHAR,Types.CHAR, Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
        return template.update(sql, params,types);
    }

    // 删除
    public  int UserDelete(int id){
        String sql ="DELETE FROM `user` WHERE id = ?";
        return template.update(sql, id);
    }



}
