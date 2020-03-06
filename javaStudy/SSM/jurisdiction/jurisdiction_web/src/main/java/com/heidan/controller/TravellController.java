package com.heidan.controller;

import com.heidan.entity.Traveller;
import com.heidan.service.ITravellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Create by heidan on 2019/12/30 11:11
 */
@Controller
@RequestMapping("/travell")
public class TravellController {

    @Autowired
    private ITravellService iTravellService;

    @RequestMapping(value = "finInId", produces = {"application/json;charset=UTF-8"})
    public Object finInId(Integer id) {
        List<Traveller> travellers = iTravellService.finInId(id);
        for (Traveller traveller : travellers) {
            System.out.println("controller返回结果:" + traveller);
        }
        return "";
    }
}
