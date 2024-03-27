package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Taskmanager;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.TaskmanagerMapper;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.service.TaskmanagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author huang
* @description 针对表【taskmanager】的数据库操作Service实现
* @createDate 2024-03-26 16:24:43
*/
@Service
public class TaskmanagerServiceImpl extends ServiceImpl<TaskmanagerMapper, Taskmanager>
    implements TaskmanagerService{

    @Resource
    TaskmanagerMapper taskmanagerMapper;
    @Resource
    PersoninfoService personinfoService;
    @Override
    public JSONObject saveTaskInfo(JSONObject request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (request.get("taskTitle") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请填写任务名称信息！");
        }
        if (request.get("taskContent") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请填写任务内容信息！");
        }
        if (request.get("taskRequire") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请填写任务要求信息！");
        }
        // 获取个人信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        Taskmanager taskmanager;
        if (request.get("iTMId") != null){
            taskmanager = taskmanagerMapper.selectById(request.getInteger("iTMId"));
        } else {
            taskmanager = new Taskmanager();
            taskmanager.setCTMManagerCodes(curUserInfo.getString("code"));
            taskmanager.setDTMCreateTime(new Date());
            taskmanager.setCTMCreaterCode(curUserInfo.getString("code"));
        }
        taskmanager.setDTMUpdateTime(new Date());
        taskmanager.setCTMUpdaterCode(curUserInfo.getString("code"));
        taskmanager.setCTMTitle(request.getString("taskTitle"));
        taskmanager.setCTMRequest(request.getString("taskRequire"));
        taskmanager.setCTMContent(request.getString("taskContent"));

        // 保存数据
        if (request.get("iTMId") != null) {
            taskmanagerMapper.updateById(taskmanager);
        } else {
            taskmanagerMapper.insert(taskmanager);
        }
        JSONObject result = new JSONObject();
        result.put("message","操作成功!");
        return result;
    }

    @Override
    public JSONObject getInfoList(JSONObject request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<JSONObject> infoList = taskmanagerMapper.getInfoList(request);
        int amount = taskmanagerMapper.getInfoListAmount(request);
        JSONObject result = new JSONObject();
        result.put("infoList",infoList);
        result.put("amount",amount);
        return result;
    }
}




