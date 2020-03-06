package com.heidan.service.impl;

import com.heidan.common.MServerResponse;
import com.heidan.dao.BargainActivityMapper;
import com.heidan.dao.BargainKillMapper;
import com.heidan.dao.BargainProductMapper;
import com.heidan.dao.UserInfoMapper;
import com.heidan.entity.BargainActivity;
import com.heidan.entity.BargainKill;
import com.heidan.entity.BargainProduct;
import com.heidan.entity.UserInfo;
import com.heidan.enums.ResultEnum;
import com.heidan.service.IBargainActivityService;
import com.heidan.util.BigDecimalUtil;
import com.heidan.util.kanjia.PingDuoDuoReduceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * Create by heidan on 2020/1/6 15:31
 */
@Service
public class BargainActivityServiceImpl implements IBargainActivityService {

    @Autowired
    private BargainActivityMapper bargainActivityMapper;

    @Autowired
    private BargainProductMapper bargainProductMapper;


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BargainKillMapper bargainKillMapper;

    @Override
    public MServerResponse selectByPrimaryKey(Integer id) {

        return MServerResponse.createBySuccess(bargainActivityMapper.selectByPrimaryKey(id));
    }

    @Override
    public MServerResponse selectByidAndUserId(Integer id, Integer userId) {
        return MServerResponse.createBySuccess(bargainActivityMapper.selectByidAndUserId(id,userId));
    }

    private Boolean startBargain(Integer userActivityId,BargainProduct bargainProduct){
        Boolean judge=true;
        // 查询用户发起砍价的信息
        BargainActivity bargainActivity = bargainActivityMapper.selectByPrimaryKey(userActivityId);

        PingDuoDuoReduceRule rule = new PingDuoDuoReduceRule(bargainProduct.getPercentFirstReduce(),bargainProduct.getFirstReduce());
        List<BigDecimal> list = rule.getReduceList(BigDecimalUtil.sub(bargainProduct.getOriginalPrice().doubleValue(),bargainProduct.getPresentPrice().doubleValue()), bargainProduct.getBargainNmber());
        for (int count=0;count<bargainProduct.getBargainNmber();count++){
            BargainKill bargainKill = new BargainKill();
            bargainKill.setBargainActivityId(userActivityId);
            bargainKill.setSortOrder(bargainProduct.getBargainNmber()-count);
            bargainKill.setMoney(list.get(count));
            bargainKill.setStatus("1");

            // 插入的砍价表中
            int rowCount = bargainKillMapper.insertSelective(bargainKill);
            if (rowCount==0){
                judge=false;
            }
        }
        return judge;
    }

    @Override
    public MServerResponse insertSelective(Integer id, Integer userId) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        //判断当前用户是否在砍价中
        BargainActivity bargainActivity = bargainActivityMapper.selectByidAndUserId(id,userInfo.getId());
        System.out.println(bargainActivity);
        //判断当前活动是否还有，或者剩余次数是否小于一
        BargainProduct bargainProduct = bargainProductMapper.selectByPrimaryKey(id);
        System.out.println(bargainProduct);
        if(!isEmpty(bargainActivity)||isEmpty(bargainProduct)|| bargainProduct.getProductNumber()<1){
            return MServerResponse.createByErrorCodeMessage(ResultEnum.Have_Kanjia.getCode(),ResultEnum.Have_Kanjia.getMessage());
        }

        // 统计数据
        BargainActivity bargainActivitys = new BargainActivity();
        bargainActivitys.setBargainProductId(id);
        bargainActivitys.setReduceTimes(bargainProduct.getBargainNmber());
        bargainActivitys.setLeftTimes(bargainProduct.getBargainNmber());
        bargainActivitys.setUserId(userInfo.getId());
        bargainActivitys.setNickName(userInfo.getUserName());
        bargainActivitys.setStatus("1");

        System.out.println("bargainActivitys:" + bargainActivitys);

        // 插入到用户发起的砍价表
        int i = bargainActivityMapper.insertSelective(bargainActivitys);
        System.out.println(i);
        System.out.println("插入后的数据:"+bargainActivitys.toString());
        if (i==1){
            // 插入成功之后给发布砍价的产品数量-1
            bargainProduct.setProductNumber(bargainProduct.getProductNumber()-1);
            bargainProductMapper.updateByPrimaryKeySelective(bargainProduct);

            // 生成砍价数据  参数1 ：用户发起砍价的ID  参数2：砍价活动的一些信息
            startBargain(bargainActivitys.getId(),bargainProduct);
            return MServerResponse.createBySuccess("发起砍价成功，快分享吧！",bargainActivitys);
        }

        return MServerResponse.createByErrorMessage("创建失败");
    }

    @Override
    public MServerResponse bargian(Integer id, Integer userId) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (userInfo == null){
            return MServerResponse.createByErrorMessage("用户不存在");
        }
        // 1.判断有这个发起砍价记录和发起砍价的状态是否是砍价中
        BargainActivity bargainActivity = bargainActivityMapper.selectByStatus(id);
        if (bargainActivity==null){
            return MServerResponse.createByErrorMessage("当前砍价已结束");
        }
        if (bargainActivity.getUserId().equals(userId)){
            return MServerResponse.createByErrorMessage("发起人不能砍自己的产品");
        }
        // 3.判断当前用户是否参加过这次的砍价
        BargainKill bargainKill = bargainKillMapper.selectByUserIdandbarginActivityId(id, userId);
        if (bargainKill!=null){
            return MServerResponse.createByErrorMessage("请勿重复砍价哦");
        }
        // 4.获取当前砍价排序最高的一个砍价数据
        BargainKill bargainKill1 = bargainKillMapper.selectByMaxSortOrderandBargainActivityId(id);
        if (bargainKill1 == null){
            return MServerResponse.createByErrorMessage("商品已砍玩");
        }
        bargainKill1.setStatus("2");
        bargainKill1.setUserId(userInfo.getId());
        bargainKill1.setUserName(userInfo.getUserName());

        // 5. 给当前用户插入到砍价中
        int i = bargainKillMapper.updateByPrimaryKeySelective(bargainKill1);

        if (i==0){
            return MServerResponse.createByErrorMessage("砍价失败，请联系管理员");
        }

        // 6.砍价次数 -1
        Integer leftTimes = bargainActivity.getLeftTimes() - 1;
        bargainActivity.setLeftTimes(leftTimes);
        // 7.如果-1 之后小于1 砍价结束修改状态
        if (leftTimes<1){
            System.out.println("最后一个砍价名额砍价成功！砍价结束");
            bargainActivity.setStatus("2");
        }

        // 8.修改当前用户发起砍价产品的状态
        int i1 = bargainActivityMapper.updateByPrimaryKeySelective(bargainActivity);

        if (i1==1){
            return MServerResponse.createBySuccess("砍价成功！",bargainKill1);
        }

        return MServerResponse.createByErrorMessage("失败");
    }

    @Override
    public MServerResponse selectListByUserId(Integer userId) {
        List<BargainActivity> bargainActivities = bargainActivityMapper.selectListByUserId(userId);

        return MServerResponse.createBySuccess(bargainActivities);
    }

}
