package com.example.teamassistantbackend.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.exception.BusinessException;
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

    /**
     * 保存信息收集表单（按stage状态判断是新增还是修改）
     */
    @RequestMapping("/saveCollectDesign")
    @ResponseBody
    public BaseResponse saveCollectDesign(@RequestBody JSONObject request){
        ArrayList<JSONObject> containerData = (ArrayList<JSONObject>)request.get("container");
        if (containerData.isEmpty()) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"不允许创建空表单！");
        }
        JSONObject result = collectInfoService.saveCollectInfo(containerData,"add".equals(request.get("state")));
        if (result.get("msg")!=null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,(String)result.get("msg"));
        } else {
            return ResultUtils.success(result);
        }
    }

    /**
     * 保存表单发布配置
     */
    @RequestMapping("/saveCollectSetting")
    @ResponseBody
    public BaseResponse<JSONObject> saveCollectSetting(@RequestBody JSONObject request){
        if (((ArrayList<JSONObject>)request.get("container")).size() == 0) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"不允许创建空表单！");
        }
        JSONObject result = collectInfoService.saveCollectInfo((ArrayList<JSONObject>)request.get("container"),true);
        if (result.get("msg")!=null) {
            return ResultUtils.error(ErrorCode.OPERATION_ERROR,(String)request.get("msg"));
        } else {
            return ResultUtils.success(result);
        }
    }
}
