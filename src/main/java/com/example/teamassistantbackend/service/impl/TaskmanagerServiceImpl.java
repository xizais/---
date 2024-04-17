package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Organizationinfo;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.example.teamassistantbackend.entity.Taskmanager;
import com.example.teamassistantbackend.entity.Worktask;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.TaskmanagerMapper;
import com.example.teamassistantbackend.service.*;
import com.example.teamassistantbackend.utils.StringUtils;
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
    @Resource
    FileinfoService fileinfoService;

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

        // 处理文件信息
        if (request.get("fileIds") != null && StringUtils.isNotEmpty(request.getString("fileIds"))) {
            JSONObject handleInfo = new JSONObject();
            handleInfo.put("type","task");
            handleInfo.put("typeId",taskmanager.getITMId());
            handleInfo.put("fileIds",request.getString("fileIds"));
            fileinfoService.handleFile(handleInfo);
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
        int iTMId = request.getInteger("iTMId");
        JSONObject result = new JSONObject();
        if ("approval".equals(request.getString("state")) || "approvalView".equals(request.getString("state"))) {
            iTMId = worktaskService.getById(iTMId).getTypeid();
        }
        Taskmanager taskmanager = taskmanagerMapper.selectById(iTMId);
        if (taskmanager == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 获取文件资源
        JSONObject fileInfo = new JSONObject();
        fileInfo.put("type","task");
        fileInfo.put("typeId",iTMId);
        List<JSONObject> fileList = fileinfoService.getFileInfoList(fileInfo);

        List<JSONObject> fileListDo = new ArrayList<>();
        // 获取任务完成文件信息
        if (request.get("workTaskId") != null) {
            int workTaskId = request.getInteger("workTaskId");
            if ("approval".equals(request.getString("state")) || "approvalView".equals(request.getString("state"))) {
                // 封装审批结果
                result.put("approvalDescription",worktaskService.getById(workTaskId).getContent());
                workTaskId = worktaskService.getById(workTaskId).getTypeid(); // 更新为目标工作待办
            }
            fileInfo.put("type","doTask");
            fileInfo.put("typeId",workTaskId);
            fileListDo = fileinfoService.getFileInfoList(fileInfo);
        }

        result.put("cTMTitle",taskmanager.getCTMTitle());
        result.put("cTMContent",taskmanager.getCTMContent());
        result.put("cTMRequest",taskmanager.getCTMRequest());
        result.put("fileList",fileList);
        result.put("fileListDo",fileListDo);
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

    @Override
    public JSONObject handleTask(JSONObject request) {
        if (request == null || request.get("iTMId") == null || request.get("workTaskId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 获取个人信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();

        // 处理任务
        // 获取工作任务信息
        Worktask worktask = worktaskService.getById(request.getInteger("workTaskId"));
        if (worktask == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        worktask.setState("完成");
        worktask.setUpdatetime(new Date());
        worktaskService.updateById(worktask);
        // 判断是否需要审批(根据发布配置进行判断0
        Pubconfig pubconfig = pubconfigService.getPubConfigByData("Task",worktask.getTypeid());
        if (pubconfig == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if ("true".equals(pubconfig.getCIsApproval())) {
            // 增加一条审批待办
            Taskmanager taskmanager = taskmanagerMapper.selectById(worktask.getTypeid());
            boolean isDefaultHandle = true; // 是否默认处理
            String handler = null;
            // 判断是否启用组织审批管理
            if ("true".equals(pubconfig.getCIsOrgManger())) {
                // 默认查找对应的发布组织，所属组织存在则由部门组织进行审核，否则由该任务的负责人进行审核
                // 查询当前人员所属的组织
                List<String> orgCodesByPubConfig = organizationinfoService.getOrgCodesByPubConfig(pubconfig);
                if (!orgCodesByPubConfig.isEmpty()) {
                    String orgCode = organizationinfoService.selectPersonExitOrgs(curUserInfo.getString("code"), String.join(",", orgCodesByPubConfig));
                    if (StringUtils.isNotEmpty(orgCode)) {
                        // 获取组织负责人
                        JSONObject orgManageInfo = organizationinfoService.getOrgManager(orgCode);
                        // 增加工作待办数据
                        handler = orgManageInfo.getString("code");
                        // 修改处理方式
                        isDefaultHandle = false;
                    }
                }
            }
            if (isDefaultHandle) {
                // 默认任务创建人员进行管理
                handler = taskmanager.getCTMManagerCodes().split(",")[0]; // 默认取第一个负责人
            }
            Worktask workTaskApproval = new Worktask();
            workTaskApproval.setType("TaskApproval");
            workTaskApproval.setTypeid(worktask.getId());// 对应工作任务ID
            workTaskApproval.setCode(handler);
            workTaskApproval.setUpdatetime(new Date());
            workTaskApproval.setContent(curUserInfo.getString("name") + "已完成《"+taskmanager.getCTMTitle()+"》任务，请处理审批！");
            worktaskService.save(workTaskApproval);
        }

        // 处理文件信息
        if (request.get("fileDoIds") != null && StringUtils.isNotEmpty(request.getString("fileDoIds"))) {
            JSONObject handleInfo = new JSONObject();
            handleInfo.put("type","doTask");
            handleInfo.put("typeId",worktask.getTypeid());
            handleInfo.put("fileIds",request.getString("fileDoIds"));
            fileinfoService.handleFile(handleInfo);

            // 修改文件的关联表ID为工作待办ID
            fileinfoService.updateFileManagerTypeId(request.getString("fileDoIds"),worktask.getId());
        }

        JSONObject result = new JSONObject();
        result.put("message","操作成功！");
        return result;
    }

    @Override
    public JSONObject approvalTask(JSONObject request) {
        if (request == null
                || request.get("isPass") == null
                || request.get("iTMId") == null
                || request.get("workTaskId") == null
        ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 查询当前工作待办数据
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        int iTMId = request.getInteger("iTMId");
        int workTaskId = request.getInteger("workTaskId");
        boolean isPass = request.getBoolean("isPass");
        Worktask curWorkTask = worktaskService.getById(workTaskId);
        Worktask targetWorkTask = worktaskService.getById(iTMId);
        if (isPass) {
            targetWorkTask.setUpdatetime(new Date());
            targetWorkTask.setContent(targetWorkTask.getContent() + curUserInfo.getString("name")+"已审批通过！");
            worktaskService.updateById(targetWorkTask);
        } else {
            Taskmanager taskmanager = taskmanagerMapper.selectById(targetWorkTask.getTypeid());
            // 增加一条新的待办
            Worktask worktask = new Worktask();
            worktask.setType(targetWorkTask.getType());
            worktask.setTypeid(targetWorkTask.getTypeid());
            worktask.setCode(targetWorkTask.getCode());
            worktask.setUpdatetime(new Date());
            worktask.setContent("任务《"+taskmanager.getCTMTitle()+"》审批不通过，理由如下："+request.getString("approvalDescription"));
            worktaskService.save(worktask);

        }
        // 更新当前的任务
        curWorkTask.setState("完成");
        curWorkTask.setUpdatetime(new Date());
        curWorkTask.setContent(curWorkTask.getContent() + " 审批结果：" + (isPass?"审批通过":"审批不通过") + "，理由如下：" + request.getString("approvalDescription"));
        worktaskService.updateById(curWorkTask);

        JSONObject result= new JSONObject();
        result.put("message","审批成功！");
        return result;
    }
}




