package com.heidan.dao;

import com.heidan.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Create by heidan on 2020/1/3 21:28
 */
@Mapper
public interface UsersMapper {
    @Select("select * from users")
    List<Users> queryUserList();
}
