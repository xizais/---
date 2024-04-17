package com.example.teamassistantbackend.service.impl;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Infoform;
import com.example.teamassistantbackend.entity.Noticemanager;
import com.example.teamassistantbackend.entity.Taskmanager;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.InfoformMapper;
import com.example.teamassistantbackend.mapper.NoticemanagerMapper;
import com.example.teamassistantbackend.mapper.TaskmanagerMapper;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.service.PubconfigService;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.example.teamassistantbackend.mapper.PubconfigMapper;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
    @Resource
    InfoformMapper infoformMapper;
    @Resource
    TaskmanagerMapper taskmanagerMapper;
    @Resource
    NoticemanagerMapper noticemanagerMapper;
    @Value("${Variable.sysManager}")
    private String sysManager;

    @Override
    public JSONObject savePubConfig(JSONObject request) {
        // 检查数据
        checkPubConfig(request);
        // 获取用户信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        // 保存或修改信息
        QueryWrapper<Pubconfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("iTypeId",request.getInteger("iTypeId"));
        queryWrapper.eq("cType",request.getString("cType"));
        queryWrapper.eq("dataState","0");
        Pubconfig pubconfig = pubconfigMapper.selectOne(queryWrapper);
        boolean isAdd = pubconfig == null;
        if (isAdd) {
            pubconfig = new Pubconfig();
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
//        if (request.get("iMaxRecordCount") != null) {
//            pubconfig.setIMaxRecordCount(request.getInteger("iMaxRecordCount"));
//        }
        if (request.get("cIsOrgManger") != null) {
            pubconfig.setCIsOrgManger(request.getString("cIsOrgManger"));
        }
        if (request.get("cPuber") != null) {
            pubconfig.setCPuber(request.getString("cPuber"));// 前端下拉选择
        }
        if (request.get("cPubToPerson") != null) {
            pubconfig.setCPubToPerson(request.getString("cPubToPerson"));
            pubconfig.setCPubToPersonCode(request.getString("cPubToPersonCode"));
        }
        if (request.get("cPubToOrg") != null) {
            pubconfig.setCPubToOrg(request.getString("cPubToOrg"));
            pubconfig.setCPubToOrgCode(request.getString("cPubToOrgCode"));
        }
        if (request.get("cPubToFlag") != null) {
            pubconfig.setCPubToFlag(request.getString("cPubToFlag"));
            pubconfig.setCPubToFlagCode(request.getString("cPubToFlagCode"));
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

        // 更新对应表的修改时间
        switch (request.getString("cType")) {
            case "CollectInfo": {
                Infoform infoform = infoformMapper.selectById(request.getInteger("iTypeId"));
                infoform.setCIFUpdateTime(new Date());
                infoform.setCIF_cPICode_update(curUserInfo.getString("code"));
                infoformMapper.updateById(infoform);
                break;
            }
            case "Task": {
                Taskmanager taskmanager = taskmanagerMapper.selectById(request.getInteger("iTypeId"));
                taskmanager.setCTMUpdaterCode(curUserInfo.getString("code"));
                taskmanager.setDTMUpdateTime(new Date());
                taskmanagerMapper.updateById(taskmanager);
                break;
            }
            case "Notify": {
                Noticemanager noticemanager = noticemanagerMapper.selectById(request.getInteger("iTypeId"));
                noticemanager.setCNMUpdaterCode(curUserInfo.getString("code"));
                noticemanager.setDNMUpdateTime(new Date());
                noticemanagerMapper.updateById(noticemanager);
                break;
            }

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
        // 数据处理、转换
        if (pubConfig!=null) {
            pubConfig.put("cPubToPerson",pubConfig.getString("cPubToPerson").split(","));
            pubConfig.put("cPubToOrg",pubConfig.getString("cPubToOrg").split(","));
            pubConfig.put("cPubToFlag",pubConfig.getString("cPubToFlag").split(","));
            pubConfig.put("cPubToPersonCode",pubConfig.getString("cPubToPersonCode").split(","));
            pubConfig.put("cPubToOrgCode",pubConfig.getString("cPubToOrgCode").split(","));
            pubConfig.put("cPubToFlagCode",pubConfig.getString("cPubToFlagCode").split(","));
            pubConfig.put("cIsApproval",pubConfig.getBoolean("cIsApproval"));
            pubConfig.put("cIsOrgManger",pubConfig.getBoolean("cIsOrgManger"));
        }
        JSONObject result = new JSONObject();
        result.put("pubConfig",pubConfig);
        return result;
    }

    @Override
    public JSONObject getPubObjectList() {
        // 获取用户信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        JSONObject result = new JSONObject();
        // 查询人员信息
        curUserInfo.put("sys",curUserInfo.getString("code").equals(sysManager)?"all":null);
        result.put("persons",pubconfigMapper.getPersonsList(curUserInfo));
        // 查询组织信息





        // 查询标签信息



        return result;
    }

    @Override
    public Pubconfig checkPubInfo(JSONObject request) {
        // 查询是否有相应的发布配置
        QueryWrapper<Pubconfig> pubconfigQueryWrapper = new QueryWrapper<>();
        pubconfigQueryWrapper.eq("iTypeId", request.getString("iTypeId"));
        pubconfigQueryWrapper.eq("cType", request.getString("cType"));
        pubconfigQueryWrapper.eq("dataState", "0");
        Pubconfig pubconfig = pubconfigMapper.selectOne(pubconfigQueryWrapper);

        boolean isNotify = "Notify".equals(request.getString("cType"));
        if (pubconfig == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "不存在发布配置信息！");
        }
        // 查询发布配置是否完整
        if (pubconfig.getDPubStartTime() == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "配置发布开始时间不存在，请进行完善！");
        }
        if (!isNotify && pubconfig.getDPubEndTime() == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "配置发布停止时间不存在，请进行完善！");
        }
        if (StringUtils.isEmpty(pubconfig.getCIsApproval())){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "配置是否进行审核不存在，请进行完善！");
        }
        if (StringUtils.isEmpty(pubconfig.getCIsOrgManger())){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "配置是否启用组织管理不存在，请进行完善！");
        }
        if (StringUtils.isEmpty(pubconfig.getCPuber())){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "配置发布者不存在，请进行完善！");
        }
//        // 检查发布时间应该大于当前时间 -- 》 去掉：默认在当前时间之前的就直接发布
//        if (pubconfig.getDPubStartTime().before(new Date())) {
//            throw new BusinessException(ErrorCode.OPERATION_ERROR,"发布时间应大于当前时间");
//        }
        // 检查发布时间是否小于停止时间
        if (!isNotify && pubconfig.getDPubEndTime().before(pubconfig.getDPubStartTime())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "发布时间不允许大于停止时间！");
        }
        boolean exitPubObject = false;
        if (!StringUtils.isEmpty(pubconfig.getCPubToPerson())) {
            exitPubObject = true;
        }
        if (!exitPubObject&&!StringUtils.isEmpty(pubconfig.getCPubToOrg())) {
            exitPubObject = true;
        }
        if (!exitPubObject&&!StringUtils.isEmpty(pubconfig.getCPubToFlag())) {
            exitPubObject = true;
        }
        if (!exitPubObject) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"发布配置中不存在发布对象，请完善！");
        }
        return pubconfig;
    }

    @Override
    public void checkPubStopTime(String type, Integer iIFId) {
        QueryWrapper<Pubconfig> pubconfigQueryWrapper = new QueryWrapper<>();
        pubconfigQueryWrapper.eq("cType",type);
        pubconfigQueryWrapper.eq("iTypeId",iIFId);
        Pubconfig pubconfig = pubconfigMapper.selectOne(pubconfigQueryWrapper);
        if (pubconfig.getDPubEndTime().before(new Date())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"当前时间已超过发布停止时间，不允许重新发布！");
        }
    }

    @Override
    public Pubconfig getPubConfigByData(String type, Integer typeId) {
        QueryWrapper<Pubconfig> pubconfigQueryWrapper = new QueryWrapper<>();
        pubconfigQueryWrapper.eq("cType",type);
        pubconfigQueryWrapper.eq("iTypeId",typeId);
        List<Pubconfig> pubconfigs = pubconfigMapper.selectList(pubconfigQueryWrapper);
        if (pubconfigs.isEmpty()) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"发布配置不存在！");
        }
        return pubconfigs.get(0);
    }

    @Override
    public void deletePubConfig(String iTypeId, String cType) {
        QueryWrapper<Pubconfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cType",cType);
        queryWrapper.eq("iTypeId",iTypeId);
        queryWrapper.eq("dataState","0");
        Pubconfig pubconfig = pubconfigMapper.selectOne(queryWrapper);
        if (pubconfig!=null) {
            pubconfig.setDataState("1");
            pubconfigMapper.updateById(pubconfig);
        }
    }

    private void checkPubConfig(JSONObject request) {
        if (request.get("iTypeId") == null || request.get("cType") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean isNotify = "Notify".equals(request.getString("cType"));
        if (request.get("dPubStartTime") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请选择发布开始时间！");
        }
        if (!isNotify && request.get("dPubEndTime") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请选择发布停止时间！");
        }
        if (!isNotify&&request.getTimestamp("dPubEndTime").before(request.getTimestamp("dPubStartTime"))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"发布时间应小于停止时间！");
        }
//        // 发布时间小于结束时间 --》 去掉：原因默认立即发布
//        // 发布时间大于当前时间
//        if (!isNotify && request.getTimestamp("dPubStartTime").before(new Date())) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR,"发布时间应大于当前时间！");
//        }
        String tableName = "";
        String condition = "";
        // 如果已经发布，则不允许进行保存
        switch (request.getString("cType")) {
            case "CollectInfo":{
                tableName = "infoform";
                condition = " and iIFId = "+request.getString("iTypeId")+" and cIFState = '发布'";
                break;
            }
            case "Notify":{
                tableName = "noticemanager";
                condition = " and iNMId = "+request.getString("iTypeId")+" and cNMState = '发布'";
                break;
            }
            case "Task":{
                tableName = "taskmanager";
                condition = " and iTMId = "+request.getString("iTypeId")+" and cTMState = '发布'";
                break;
            }
        }
        boolean result = pubconfigMapper.checkDataIsPub(condition,tableName);
        if (result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"已发布，不允许进行发布配置修改！");
        }
    }
}




