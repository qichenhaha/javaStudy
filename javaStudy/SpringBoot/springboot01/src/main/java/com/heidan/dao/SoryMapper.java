package com.heidan.dao;

import com.heidan.entity.Sory;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Create by heidan on 2020/1/7 12:03
 */
@Component
public interface SoryMapper {

    @Select("select * from sory")
    List<Sory> liatAll();
}
