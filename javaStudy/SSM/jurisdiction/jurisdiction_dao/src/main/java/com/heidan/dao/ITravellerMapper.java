package com.heidan.dao;

import com.heidan.entity.Traveller;

import java.util.List;

/**
 * Create by heidan on 2019/12/30 11:01
 */

public interface ITravellerMapper {
    List<Traveller> finInId(Integer id);
}
