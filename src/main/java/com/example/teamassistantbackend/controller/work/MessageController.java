package com.example.teamassistantbackend.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.service.ChatinfoService;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: xizai
 * @Date: 2024-04-19
 */
@Controller
@RequestMapping("/Message")
public class MessageController {
    @Resource
    ChatinfoService chatinfoService;

    /**
     * 获取人员聊天信息
     */
    @PostMapping("/getMessage")
    @ResponseBody
    public BaseResponse<JSONObject> getMessage(){
        return ResultUtils.success(chatinfoService.getMyMessage());
    }

    /**
     * 新增人员来聊天信息
     */
    @PostMapping("/saveMessage")
    @ResponseBody
    public BaseResponse<JSONObject> saveMessage(@RequestBody JSONObject request){
        return ResultUtils.success(chatinfoService.saveMessage(request));
    }
}
