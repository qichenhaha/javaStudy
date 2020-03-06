package com.heidan.test;

import com.heidan.SpringbootApplication;
import com.heidan.dao.BargainActivityMapper;
import com.heidan.dao.BargainProductMapper;
import com.heidan.dao.SoryMapper;
import com.heidan.entity.BargainActivity;
import com.heidan.entity.BargainProduct;
import com.heidan.entity.Sory;
import com.heidan.util.DistanceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

/**
 * Create by heidan on 2020/1/3 21:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class MapperTest {
    @Autowired
    private BargainProductMapper bargainProductMapper;


    @Autowired
    private BargainActivityMapper bargainActivityMapper;


    @Autowired
    private SoryMapper soryMapper;

    @Test
    public void test() {
        List<BargainProduct> bargainProducts = bargainProductMapper.listAll();
        for (BargainProduct bargainProduct : bargainProducts) {
            System.out.println(bargainProduct.toString());
        }
    }

    @Test
    public void test1() {
        BargainActivity bargainActivity = bargainActivityMapper.selectByidAndUserId(1,213);
        System.out.println(bargainActivity.toString());
    }

    @Test
    public void test2() {
        BargainActivity bargainActivity = new BargainActivity();
        bargainActivity.setBargainProductId(1);
        bargainActivity.setReduceTimes(10);
        bargainActivity.setLeftTimes(10);
        bargainActivity.setUserId(213);
        bargainActivity.setNickName("李氏");
        bargainActivity.setStatus("1");
        int i = bargainActivityMapper.insertSelective(bargainActivity);

        System.out.println(bargainActivity.toString());
        System.out.println(i);

    }

    @Test
    public void test3() {
        BargainActivity bargainActivity = bargainActivityMapper.selectByStatus(9);
        System.out.println(bargainActivity.toString());
    }


    @Test
    public void test4() {
        double latitude = 31.863930;
        double longitude = 117.344810;
        List<Sory> sories = soryMapper.liatAll();
        for (Sory sory : sories) {
            double distance = DistanceUtil.getDistance(longitude, latitude, sory.getLongitude(), sory.getLatitude());
            sory.setDistance(distance);
            System.out.println(sory);
        }

        Collections.sort(sories);


        System.out.println("排序后=============================");
        for (Sory sory : sories) {
            System.out.println(sory);
        }



    }
}
