package com.heidan.controller;

import com.heidan.entity.Member;
import com.heidan.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by heidan on 2019/12/30 10:48
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private IMemberService iMemberServices;

    @RequestMapping("findById")
    public Object findById(Integer id) {
        System.out.println("成功进入");
        System.out.println("id==>" + id);
        Member byId = iMemberServices.findById(id);
        System.out.println("controller获取数据:" + byId);
        return "";
    }
}
