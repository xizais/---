package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.teamassistantbackend.entity.Worktask;

/**
* @author 希仔
* @description 针对表【worktask(工作待办表)】的数据库操作Service
* @createDate 2024-03-17 22:29:29
*/
public interface WorktaskService extends IService<Worktask> {

    JSONObject handleWorkTask(JSONObject request);

    JSONObject getWorkInfoList(JSONObject request);

    JSONObject deleteWorkTask(JSONObject request);

    void deleteWorkTaskAll(String typeId, String type);
}
