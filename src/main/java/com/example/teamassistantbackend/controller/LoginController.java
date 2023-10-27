package com.example.teamassistantbackend.controller;

import com.example.teamassistantbackend.service.PersoninfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 登入控制层：
 * @author huang
 */
@Controller
@RequestMapping("/Login")
public class LoginController {
    @Resource
    PersoninfoService personinfoService;

    /**
     * 用户登入
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public Boolean doLogin(String account, String password){
        System.out.println(account+password);
        return false;
    }
}
