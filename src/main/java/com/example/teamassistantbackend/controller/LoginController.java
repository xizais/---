package com.example.teamassistantbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.entity.requestCommon.UserInfo;
import com.example.teamassistantbackend.service.PersoninfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登入控制层：
 * @author huang
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    @Resource
    PersoninfoService personinfoService;

    /**
     * 用户登入
     */
    @PostMapping("/login")
    @ResponseBody
    public BaseResponse<JSONObject> userLogin(@RequestBody UserInfo userInfo, HttpServletRequest request){
        return ResultUtils.success(personinfoService.userLogin(userInfo.getCurCode(), userInfo.getCurPassword(), request));
    }

    @GetMapping("/logout")
    @ResponseBody
    public BaseResponse<String> logout(){
        personinfoService.logout();
        return ResultUtils.success("操作成功！");
    }
}
