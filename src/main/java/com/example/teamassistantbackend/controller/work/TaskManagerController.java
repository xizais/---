package com.example.teamassistantbackend.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.service.TaskmanagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: xizai
 * @Date: 2024-03-27
 */
@Controller
@RequestMapping("/TaskManager")
public class TaskManagerController {
    @Resource
    TaskmanagerService taskmanagerService;

    /**
     * 处理工作任务
     */
    @PostMapping("/saveTask")
    @ResponseBody
    public BaseResponse<JSONObject> saveTask(@RequestBody JSONObject request){
        return ResultUtils.success(taskmanagerService.saveTaskInfo(request));
    }

    /**
     * 获取任务列表
     */
    @PostMapping("/getTaskInfoList")
    @ResponseBody
    public BaseResponse<JSONObject> getTaskInfoList(@RequestBody JSONObject request){
        return ResultUtils.success(taskmanagerService.getInfoList(request));
    }
}
