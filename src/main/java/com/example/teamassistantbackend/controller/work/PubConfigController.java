package com.example.teamassistantbackend.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.service.PubconfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: xizai
 * @Date: 2024-03-13
 */
@Controller
@RequestMapping("/PubConfig")
public class PubConfigController {
    @Resource
    PubconfigService pubconfigService;

    @PostMapping("/savePubConfig")
    @ResponseBody
    public BaseResponse<JSONObject> saveCollectDesign(@RequestBody JSONObject request){
        return ResultUtils.success(pubconfigService.savePubConfig(request));
    }

    @PostMapping("/getPubConfigInfo")
    @ResponseBody
    public BaseResponse<JSONObject> getPubConfigInfo(@RequestBody JSONObject request){
        return ResultUtils.success(pubconfigService.getPubConfig(request));
    }

    /**
     * 获得发布对象信息，按type进行获取。
     *  type：   person 人员
     *          org 组织
     *          flag 标签
     */
    @PostMapping("/getPubObjectInfo")
    @ResponseBody
    public BaseResponse<JSONObject> getPubObjectInfo(){
        return ResultUtils.success(pubconfigService.getPubObjectList());
    }
}
