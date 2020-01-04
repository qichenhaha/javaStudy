package com.heidan.service.impl;

import com.heidan.dao.IMemberMapper;
import com.heidan.entity.Member;
import com.heidan.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by heidan on 2019/12/30 10:47
 */
@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberMapper iMemberMapper;

    @Override
    public Member findById(Integer id) {
        Member byId = iMemberMapper.findById(id);
        System.out.println(byId);
        return byId;
    }
}
