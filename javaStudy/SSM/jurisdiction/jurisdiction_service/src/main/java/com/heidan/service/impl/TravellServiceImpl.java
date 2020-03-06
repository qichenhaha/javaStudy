package com.heidan.service.impl;

import com.heidan.dao.ITravellerMapper;
import com.heidan.entity.Traveller;
import com.heidan.service.ITravellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by heidan on 2019/12/30 11:10
 */
@Service
public class TravellServiceImpl implements ITravellService {
    @Autowired
    private ITravellerMapper iTravellerMapper;

    @Override
    public List<Traveller> finInId(Integer id) {
        return iTravellerMapper.finInId(id);
    }
}
