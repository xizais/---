package com.example.teamassistantbackend.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Noticemanager;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.example.teamassistantbackend.entity.Worktask;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.NoticemanagerMapper;
import com.example.teamassistantbackend.service.*;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author huang
* @description 针对表【noticemanager】的数据库操作Service实现
* @createDate 2024-03-26 16:24:43
*/
@Service
public class NoticemanagerServiceImpl extends ServiceImpl<NoticemanagerMapper, Noticemanager>
    implements NoticemanagerService {

    @Resource
    NoticemanagerMapper noticemanagerMapper;
    @Resource
    PersoninfoService personinfoService;
    @Resource
    PubconfigService pubconfigService;
    @Resource
    OrganizationinfoService organizationinfoService;
    @Resource
    WorktaskService worktaskService;

    @Override
    public JSONObject saveNoticeInfo(JSONObject request) {
        if (request == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (request.get("title") == null || StringUtils.isEmpty(request.getString("title"))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"通知标题不允许为空！");
        }
        if (request.get("content") == null || StringUtils.isEmpty(request.getString("content"))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"通知内容不允许为空！");
        }

        // 获取当前用户
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        // 数据处理
        Noticemanager noticemanager;
        if (request.getInteger("iNMId") != 0) {
            noticemanager = noticemanagerMapper.selectById(request.getInteger("iNMId"));
        } else {
            noticemanager = new Noticemanager();
            noticemanager.setCNMManagerCodes(curUserInfo.getString("code"));
            noticemanager.setDNMCreateTime(new Date());
            noticemanager.setCNMCreaterCode(curUserInfo.getString("code"));
        }
        noticemanager.setCNMTitle(request.getString("title"));
        noticemanager.setCNMContent(request.getString("content"));
        noticemanager.setDNMUpdateTime(new Date());
        noticemanager.setCNMUpdaterCode(curUserInfo.getString("code"));
        if (request.getInteger("iNMId") == 0) {
            noticemanagerMapper.insert(noticemanager);
        } else {
            noticemanagerMapper.updateById(noticemanager);
        }
        JSONObject result = new JSONObject();
        result.put("message","保存成功!");
        result.put("iNMId",noticemanager.getINMId());
        return result;
    }

    @Override
    public JSONObject deleteNotifyInfo(JSONObject request) {
        if (request == null || request.get("iNMId") == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Noticemanager noticemanager = noticemanagerMapper.selectById(request.getString("iNMId"));
        if (!"草稿".equals(noticemanager.getCNMState())) {
            // 删除待办数据
            worktaskService.deleteWorkTaskAll(request.getString("iNMId"),"Notify");
        }
        noticemanager.setDataState("1");
        noticemanagerMapper.updateById(noticemanager);
        // 删除发布配置
        pubconfigService.deletePubConfig(request.getString("iNMId"),"Notify");
        JSONObject result = new JSONObject();
        result.put("message","删除成功!");
        return result;
    }

    @Override
    public JSONObject getInfoList() {
        // 获取当前登入人员
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        List<JSONObject> infoList = noticemanagerMapper.getInfoList(curUserInfo.getString("code"));
        JSONObject result = new JSONObject();
        result.put("infoList",infoList);
        return result;
    }

    @Override
    public JSONObject getInfo(JSONObject request) {
        if (request == null || request.get("iNMId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Noticemanager noticemanager = noticemanagerMapper.selectById(request.getInteger("iNMId"));
        JSONObject result = new JSONObject();
        result.put("info",noticemanager);
        return result;
    }

    @Override
    public JSONObject pubNotify(JSONObject request) {
        // 检查数据
        Pubconfig pubconfig = checkNotify(request);
        // 查询通知数据
        Noticemanager noticemanager = noticemanagerMapper.selectById(request.getInteger("iTypeId"));
        // 获取当前操作人
        JSONObject curUserInfo = personinfoService.getCurUserInfo();

        // 处理数据
        noticemanager.setDNMUpdateTime(new Date());
        noticemanager.setCNMUpdaterCode(curUserInfo.getString("code"));
        noticemanager.setCNMPubName(pubconfig.getCPuber());
        noticemanager.setCNMState("发布");
        noticemanagerMapper.updateById(noticemanager);

        // 通知（增加待办）
        // 获取通知对象
        List<JSONObject> personCodeList = organizationinfoService.getPersonCodeList(pubconfig.getCPubToPersonCode(), pubconfig.getCPubToOrgCode(), pubconfig.getCPubToFlagCode());
        List<Worktask> worktaskList = new ArrayList<>();
        for (JSONObject personInfo : personCodeList) {
            Worktask worktask = new Worktask();
            worktask.setType("Notify");
            worktask.setTypeid(request.getInteger("iTypeId"));
            worktask.setCode(personInfo.getString("code"));
            worktask.setUpdatetime(new Date());
            worktask.setState("待确认");
            worktask.setContent(pubconfig.getCPuber()+"发布了通知《"+noticemanager.getCNMTitle()+"》，请确认！");
            worktaskList.add(worktask);
        }
        worktaskService.saveBatch(worktaskList);
        JSONObject result = new JSONObject();
        result.put("message","发布成功！");
        return result;
    }

    @Override
    public JSONObject getDataList(JSONObject request) {
        if (request == null || request.get("typeId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<JSONObject> notifyPersonData = noticemanagerMapper.getPersonData(request);
        int doneAmont = noticemanagerMapper.getPersonDataDontMount(request);
        JSONObject result = new JSONObject();
        result.put("notifyPersonData",notifyPersonData);
        result.put("doneAmont",doneAmont);
        return result;
    }

    private Pubconfig checkNotify(JSONObject request) {
        // 检查数据是否存在
        if (request == null || request.get("iTypeId") == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        // 检查发布配置是否存在以及关键信息是否存在
        request.put("cType","Notify");
        return pubconfigService.checkPubInfo(request);
    }
}




