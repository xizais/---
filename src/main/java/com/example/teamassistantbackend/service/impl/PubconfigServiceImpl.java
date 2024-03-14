package com.example.teamassistantbackend.service.impl;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.service.PubconfigService;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.example.teamassistantbackend.mapper.PubconfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author huang
* @description 针对表【pubconfig(发布配置表)】的数据库操作Service实现
* @createDate 2024-03-13 17:41:28
*/
@Service
public class PubconfigServiceImpl extends ServiceImpl<PubconfigMapper, Pubconfig>
    implements PubconfigService {

    @Resource
    PersoninfoService personinfoService;
    @Resource
    PubconfigMapper pubconfigMapper;
    @Override
    public JSONObject savePubConfig(JSONObject request) {
        // 检查数据
        checkPubConfig(request);
        // 保存或修改信息
        // 获取用户信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        Pubconfig pubconfig = new Pubconfig();
        boolean isAdd = "add".equals(request.getString("type"));
        if (!isAdd) {
            QueryWrapper<Pubconfig> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("iTypeId",request.getInteger("iTypeId"));
            queryWrapper.eq("cType",request.getInteger("cType"));
            queryWrapper.eq("dataState","0");
            pubconfig = pubconfigMapper.selectOne(queryWrapper);
        } else {
            pubconfig.setCCreator(curUserInfo.getString("name"));
            pubconfig.setITypeId(request.getInteger("iTypeId"));
            pubconfig.setCType(request.getString("cType"));
        }

        if (request.get("dPubStartTime") != null) {
            pubconfig.setDPubStartTime(request.getTimestamp("dPubStartTime"));
        }
        if (request.get("dPubEndTime") != null) {
            pubconfig.setDPubEndTime(request.getTimestamp("dPubEndTime"));
        }
        if (request.get("cIsApproval") != null) {
            pubconfig.setCIsApproval(request.getString("cIsApproval"));
        }
        if (request.get("iMaxRecordCount") != null) {
            pubconfig.setIMaxRecordCount(request.getInteger("iMaxRecordCount"));
        }
        if (request.get("cIsOrgManger") != null) {
            pubconfig.setCIsOrgManger(request.getString("cIsOrgManger"));
        }
        if (request.get("cPuber") != null) {
            pubconfig.setCPuber(request.getString("cPuber"));// 前端下拉选择
        }
        if (request.get("cPubToPerson") != null) {
            pubconfig.setCPubToPerson(request.getString("cPubToPerson"));
        }
        if (request.get("cPubToOrg") != null) {
            pubconfig.setCPubToOrg(request.getString("cPubToOrg"));
        }
        if (request.get("cPubToFlag") != null) {
            pubconfig.setCPubToFlag(request.getString("cPubToFlag"));
        }
        if (request.get("name") != null) {
            pubconfig.setCUpdater(curUserInfo.getString("name"));
        }
        pubconfig.setDUpdateTime(new Date());
        if (isAdd) {
            pubconfigMapper.insert(pubconfig);
        } else {
            pubconfigMapper.updateById(pubconfig);
        }
        JSONObject result = new JSONObject();
        result.put("message","保存成功！");
        return result;
    }

    @Override
    public JSONObject getPubConfig(JSONObject request) {
        if (request.get("type")==null || request.get("iTypeId")==null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        JSONObject pubConfig = pubconfigMapper.selectPubConfig(request);
        JSONObject result = new JSONObject();
        result.put("pubConfig",pubConfig);
        return result;
    }

    private void checkPubConfig(JSONObject request) {
        if (request.get("iTypeId") == null || request.get("cType") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (request.get("dPubStartTime") == null || request.get("dPubEndTime") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请选择发布起始时间！");
        }
    }
}




