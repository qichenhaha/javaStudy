package com.heidan.controller;

import com.heidan.common.MServerResponse;
import com.heidan.service.IBargainActivityService;
import com.heidan.service.IBargainProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by heidan on 2020/1/6 14:00
 * 砍价模块
 */
@Controller
@RequestMapping("/bargain")
public class BargainController {


    @Autowired
    private IBargainProductService iBargainProductService;

    @Autowired
    private IBargainActivityService iBargainActivityService;

    /**
     * @return 用户查询可以砍价的产品返回列表
     */
    @ResponseBody
    @RequestMapping("listAll")
    public MServerResponse listAll(){
        return iBargainProductService.listAll();
    }

    /**
     * 用户发起砍价
     * @param id 砍价活动表的id
     * @param userId 用户的id
     * @return
     */
    @ResponseBody
    @RequestMapping("inser")
    public MServerResponse inser(Integer id,Integer userId){
        return  iBargainActivityService.insertSelective(id, userId);
    }

    /**
     * 帮发起人砍价
     * @param id 发起人砍价产品的id
     * @param userId 当前用户的id
     * @return 砍价结果
     */
    @ResponseBody
    @RequestMapping("kill")
    public MServerResponse kill(Integer id,Integer userId){
        return iBargainActivityService.bargian(id,userId);
    }

    /**
     *
     * @param userId
     * @return 当前用户发布的所有砍价产品信息
     */
    @ResponseBody
    @RequestMapping("selectListByUserId")
    public MServerResponse selectListByUserId(Integer userId){

        return iBargainActivityService.selectListByUserId(userId);
    }






}
