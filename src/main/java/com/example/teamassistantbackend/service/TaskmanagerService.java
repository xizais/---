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
}
