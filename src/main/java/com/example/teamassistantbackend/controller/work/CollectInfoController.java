package com.example.teamassistantbackend.controller.work;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.service.CollectInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
@RequestMapping("/CollectInfo")
public class CollectInfoController {
    @Resource
    CollectInfoService collectInfoService;
    @RequestMapping("/test")
    @ResponseBody
    public BaseResponse<JSONObject> test(@RequestBody JSONObject request){
        if (((ArrayList<JSONObject>)request.get("container")).size() == 0) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"不允许创建空表单！");
        }
        JSONObject result = collectInfoService.saveCollectInfo((ArrayList<JSONObject>)request.get("container"));
        if (result.get("msg")!=null) {
            return ResultUtils.error(ErrorCode.OPERATION_ERROR,(String)request.get("msg"));
        } else {
            return ResultUtils.success(result);
        }
    }
}
