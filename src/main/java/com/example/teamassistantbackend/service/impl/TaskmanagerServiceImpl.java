package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.example.teamassistantbackend.entity.Taskmanager;
import com.example.teamassistantbackend.entity.Worktask;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.TaskmanagerMapper;
import com.example.teamassistantbackend.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    PubconfigService pubconfigService;
    @Resource
    OrganizationinfoService organizationinfoService;
    @Resource
    WorktaskService worktaskService;
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
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        request.put("cPICode",curUserInfo.getString("code"));
        List<JSONObject> infoList = taskmanagerMapper.getInfoList(request);
        int amount = taskmanagerMapper.getInfoListAmount(request);
        JSONObject result = new JSONObject();
        result.put("infoList",infoList);
        result.put("amount",amount);
        return result;
    }

    @Override
    public JSONObject getTaskInfo(JSONObject request) {
        if (request == null || request.get("iTMId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Taskmanager taskmanager = taskmanagerMapper.selectById(request.getInteger("iTMId"));
        if (taskmanager == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        JSONObject result = new JSONObject();
        result.put("cTMTitle",taskmanager.getCTMTitle());
        result.put("cTMContent",taskmanager.getCTMContent());
        result.put("cTMRequest",taskmanager.getCTMRequest());
        return result;
    }

    @Override
    public JSONObject deleteTask(JSONObject request) {
        if (request == null || request.get("iTMId") == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Taskmanager taskmanager = taskmanagerMapper.selectById(request.getInteger("iTMId"));
        if (taskmanager == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        taskmanager.setDTMUpdateTime(new Date());
        taskmanager.setCTMUpdaterCode(curUserInfo.getString("code"));
        taskmanager.setDataState("1");
        taskmanagerMapper.updateById(taskmanager);

        // 删除配置信息
        pubconfigService.deletePubConfig(request.getString("iTMId"),"Task");
        JSONObject result = new JSONObject();
        result.put("message","删除成功！");
        return result;
    }

    @Override
    public JSONObject pubTask(JSONObject request) {
        if (request == null || request.get("iTypeId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 查看任务是否存在
        Taskmanager taskmanager = taskmanagerMapper.selectById(request.getInteger("iTypeId"));
        if (taskmanager == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 查看发布配置是否满足条件
        request.put("cType","Task");
        Pubconfig pubconfig = pubconfigService.checkPubInfo(request);
        // 发布任务（添加待办）
        List<JSONObject> personCodeList = organizationinfoService.getPersonCodeList(pubconfig.getCPubToPersonCode(), pubconfig.getCPubToOrgCode(), pubconfig.getCPubToFlagCode());
        List<Worktask> worktaskList = new ArrayList<>();
        for (JSONObject personInfo : personCodeList) {
            Worktask worktask = new Worktask();
            worktask.setType("Task");
            worktask.setTypeid(request.getInteger("iTypeId"));
            worktask.setCode(personInfo.getString("code"));
            worktask.setUpdatetime(new Date());
            worktask.setState("草稿");
            worktask.setContent(pubconfig.getCPuber()+"发布了任务《"+taskmanager.getCTMTitle()+"》，请完成！");
            worktaskList.add(worktask);
        }
        worktaskService.saveBatch(worktaskList);

        // 修改任务数据
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        taskmanager.setCTMUpdaterCode(curUserInfo.getString("code"));
        taskmanager.setDTMUpdateTime(new Date());
        taskmanager.setCTMPubName(pubconfig.getCPuber());
        taskmanager.setCTMState("发布");
        taskmanagerMapper.updateById(taskmanager);

        JSONObject result = new JSONObject();
        result.put("message","发布成功！");
        return result;
    }
}




