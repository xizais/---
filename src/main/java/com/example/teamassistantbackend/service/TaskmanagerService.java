package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Taskmanager;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author huang
* @description 针对表【taskmanager】的数据库操作Service
* @createDate 2024-03-26 16:24:43
*/
public interface TaskmanagerService extends IService<Taskmanager> {

    JSONObject saveTaskInfo(JSONObject request);

    JSONObject getInfoList(JSONObject request);

    JSONObject getTaskInfo(JSONObject request);

    JSONObject deleteTask(JSONObject request);

    JSONObject pubTask(JSONObject request); // 发布任务

    JSONObject handleTask(JSONObject request); // 处理个人任务信息

    JSONObject approvalTask(JSONObject request); // 审批个人任务
}
