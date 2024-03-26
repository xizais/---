package com.example.teamassistantbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: xizai
 * @Date: 2024-03-26
 */
@Controller
@RequestMapping("/File")
public class FileController {
    /**
     * 上传文件
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public BaseResponse<JSONObject> getWorkInfoList(@RequestBody JSONObject request){
        System.out.println(request);
        return ResultUtils.success(null);
    }
}
