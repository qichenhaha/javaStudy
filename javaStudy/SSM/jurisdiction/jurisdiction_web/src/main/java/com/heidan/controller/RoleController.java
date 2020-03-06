package com.heidan.controller;

import com.heidan.entity.Role;
import com.heidan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * Create by heidan on 2020/1/2 11:32
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @RequestMapping("finBynotid")
    @RolesAllowed("ADMIN")
    public Object finBynotid(Integer id) {
        System.out.println("成功进入finBynotid");
        List<Role> roles = iRoleService.finBynotid(id);
        System.out.println(roles);
        return "";
    }

}
