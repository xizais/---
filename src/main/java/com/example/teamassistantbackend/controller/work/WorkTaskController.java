package com.example.teamassistantbackend.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.service.WorktaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: xizai
 * @Date: 2024-03-25
 */
@Controller
@RequestMapping("/WorkTask")
public class WorkTaskController {
    @Resource
    WorktaskService worktaskService;

    /**
     * 处理工作任务
     */
    @PostMapping("/handleWorkTask")
    @ResponseBody
    public BaseResponse<JSONObject> saveCollectDesign(@RequestBody JSONObject request){
        return ResultUtils.success(worktaskService.handleWorkTask(request));
    }

    /**
     * 获取工作任务
     */
    @PostMapping("/getWorkInfoList")
    @ResponseBody
    public BaseResponse<JSONObject> getWorkInfoList(@RequestBody JSONObject request){
        return ResultUtils.success(worktaskService.getWorkInfoList(request));
    }

    /**
     * 删除已完成的工作记录
     */
    @PostMapping("/deleteWorkTask")
    @ResponseBody
    public BaseResponse<JSONObject> deleteWorkTask(@RequestBody JSONObject request){
        return ResultUtils.success(worktaskService.deleteWorkTask(request));
    }
}
