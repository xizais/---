package com.example.teamassistantbackend.service.impl;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.*;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.InfoformMapper;
import com.example.teamassistantbackend.mapper.InfoformcreateMapper;
import com.example.teamassistantbackend.mapper.WorkmessageMapper;
import com.example.teamassistantbackend.mapper.WorktaskMapper;
import com.example.teamassistantbackend.service.*;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CollectInfoServiceImpl implements CollectInfoService {
    @Resource
    PersoninfoService personinfoService;
    @Resource
    InfoformMapper infoformMapper;// 表单配置
    @Resource
    InfoformcreateMapper infoformcreateMapper;// 表单创建
    @Resource
    PubconfigService pubconfigService;
    @Resource
    OrganizationinfoService organizationinfoService;
    @Resource
    WorktaskService worktaskService;
    @Resource
    WorktaskMapper worktaskMapper;
    @Resource
    WorkmessageMapper workmessageMapper;

    @Override
    public JSONObject saveCollectInfo(ArrayList<HashMap<String,Object>> containers, boolean isAdd, int iIFId, String title) {
        JSONObject result = new JSONObject();
        // 1. 检查数据
        checkCollectData(containers);

        // 2. 数据处理
        // 2.2 创建配置表
        JSONObject userInfo = personinfoService.getCurUserInfo();
        String curHandlerCode = userInfo.getString("code");// 当前处理人编号
        String curHandler = userInfo.getString("name");// 当前处理人名称
        Infoform infoform = new Infoform();
        if (!isAdd) {
            infoform = infoformMapper.selectById(iIFId);
            // 删除原有的表单数据
            QueryWrapper<Infoformcreate> infoformcreateQueryWrapper = new QueryWrapper<>();
            infoformcreateQueryWrapper.eq("iIFC_iIFId",iIFId);
            infoformcreateMapper.delete(infoformcreateQueryWrapper);
        }

        infoform.setCIFTitle(title);
        infoform.setCIF_cPICode_update(curHandler);// 修改者
        infoform.setCIFUpdateTime(new Date());// 修改时间

        if (isAdd) {
            infoform.setCIF_cPICode_insert(curHandlerCode);// 创建者编号
            infoform.setCIFPuber(curHandler);// 当前处理人
            infoform.setCIFCreateTime(new Date());// 创建时间
            infoform.setCIFManager(curHandler);// 初始管理员（建议放人员编号吧）
            infoformMapper.insert(infoform);
            iIFId = infoform.getIIFId();
        } else {
            QueryWrapper<Infoform> infoformQueryWrapper = new QueryWrapper<>();
            infoformQueryWrapper.eq("iIFId",iIFId);
            infoformMapper.update(infoform,infoformQueryWrapper);
        }

        // 2.3 存储数据
        for (HashMap<String,Object> fromDataHash : containers) {
            JSONObject fromData = new JSONObject(fromDataHash);
            int containerId = 0;
            // 2.3.1 保存表单容器
            Infoformcreate containerForm = new Infoformcreate();
            containerForm.setIIFC_iIFId(iIFId);
            containerForm.setId(fromData.getInteger("id"));
            containerForm.setContainerId(containerId);
            containerForm.setWidth(fromData.getString("width"));
            containerForm.setHeight(fromData.getString("height"));
            containerForm.setShowBorder(fromData.getString("showBorder"));
            containerForm.setBorderWidth(fromData.getString("borderWidth"));
            containerForm.setShowRadius(fromData.getString("showRadius"));
            containerForm.setBorderRadius(fromData.getString("borderRadius"));
            containerForm.setMarginTop(fromData.getString("marginTop"));
            containerForm.setMarginBottom(fromData.getString("marginBottom"));
            containerForm.setMarginLeft(fromData.getString("marginLeft"));
            containerForm.setMarginRight(fromData.getString("marginRight"));
            containerForm.setType(fromData.getString("type"));
            infoformcreateMapper.insert(containerForm);
            containerId = containerForm.getIIFCId();

            // 2.3.2 保存容器内元素
            ArrayList<HashMap<String,Object>> metas = (ArrayList<HashMap<String,Object>>)fromData.get("child");
            for (HashMap<String,Object> metaHash : metas) {
                JSONObject meta = new JSONObject(metaHash);
                Infoformcreate metaForm = new Infoformcreate();
                metaForm.setIIFC_iIFId(iIFId);
                metaForm.setId(meta.getInteger("id"));
                metaForm.setContainerId(containerId);
                metaForm.setTitle(meta.getString("title"));
                metaForm.setWidth(meta.getString("width"));
                metaForm.setHeight(meta.getString("height"));
                metaForm.setShowBorder(meta.getString("showBorder"));
                metaForm.setBorderWidth(meta.getString("borderWidth"));
                metaForm.setShowRadius(meta.getString("showRadius"));
                metaForm.setBorderRadius(meta.getString("borderRadius"));
                metaForm.setMarginTop(meta.getString("marginTop"));
                metaForm.setMarginBottom(meta.getString("marginBottom"));
                metaForm.setMarginLeft(meta.getString("marginLeft"));
                metaForm.setMarginRight(meta.getString("marginRight"));
                metaForm.setTextType(meta.getString("textType"));
                metaForm.setFontSize(meta.getString("fontSize"));
                metaForm.setFontWeight(meta.getString("fontWeight"));
                metaForm.setFontFamily(meta.getString("fontFamily"));
                metaForm.setDefaultText(meta.getString("defaultText"));
                metaForm.setTextAlign(meta.getString("textAlign"));
                metaForm.setTextColor(meta.getString("textColor"));
                metaForm.setPlaceholder(meta.getString("placeholder"));
                metaForm.setMaxLength(meta.getString("maxLength"));
                metaForm.setOptions(meta.getString("options"));
                metaForm.setDefaultTime(meta.getDate("defaultTime"));
                metaForm.setIsNeed(meta.getString("isNeed"));
                metaForm.setShowInnerBorder(meta.getString("showInnerBorder"));
                metaForm.setType(meta.getString("type"));
                metaForm.setMaxOption(meta.get("maxOption")==null?0:meta.getInteger("maxOption"));
                metaForm.setMinOption(meta.get("minOption")==null?0:meta.getInteger("minOption"));
                infoformcreateMapper.insert(metaForm);
            }
        }

        // 3. 返回结果
        return result;
    }

    @Override
    public JSONObject getInfoList(JSONObject request) {
        // 获取当前用户编号
        JSONObject userInfo = personinfoService.getCurUserInfo();
        request.put("cPICode",userInfo.getString("code"));
        // 查询用户表单配置信息数据：用户自身的、用户管理的（按时间排序）
        ArrayList<JSONObject> infoList = infoformMapper.getFromList(request);
        int amount = infoformMapper.getFromListCount(request);
        for (JSONObject infoForm : infoList) {
            infoForm.put("authority",checkAuthority(infoForm));// 当前登入人是否是管理员或者创建者
        }
        if (infoList.isEmpty()) {
            infoList = new ArrayList<>();
        }
        JSONObject result = new JSONObject();
        result.put("infoList",infoList);
        result.put("amount",amount);
        return result;
    }

    @Override
    public JSONObject getInfo(String iIFId) {
        if (StringUtils.isEmpty(iIFId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ArrayList<JSONObject> containers = infoformcreateMapper.getContainerInfo(iIFId);
        for (JSONObject container : containers) {
            container.put("showBorder","true".equals(container.getString("showBorder")));
            container.put("showRadius","true".equals(container.getString("showRadius")));
            // 封装容器元素
            ArrayList<JSONObject> childs = infoformcreateMapper.getChildInfo(iIFId,container.getString("iIFCId"));
            if (!childs.isEmpty()) {
                // 处理option数据
                for (JSONObject child : childs) {
                    child.put("showBorder","true".equals(child.getString("showBorder")));
                    child.put("showRadius","true".equals(child.getString("showRadius")));
                    child.put("showInnerBorder","true".equals(child.getString("showInnerBorder")));
                    child.put("isNeed","true".equals(child.getString("isNeed")));
                    if (!StringUtils.isEmpty(child.getString("options"))) {
                        child.put("options",JSONArray.parseArray(convertToJson(child.getString("options"))));
                    }
                    if (child.getString("type").equals("checkbox")) {
                        child.put("defaultText",new ArrayList<>());
                    }
                }
                container.put("child",childs);
            }
        }
        JSONObject result = new JSONObject();
        Infoform infoForm = infoformMapper.selectById(iIFId);
        int maxMetaId = infoformcreateMapper.getMetaId(iIFId);
        result.put("infoForm",infoForm);// 表单配置信息
        result.put("containers",containers);// 表单元素信息
        result.put("maxMetaId",maxMetaId);// 元素最大值ID
        result.put("authority",checkAuthority(infoForm));// 当前登入人是否是管理员或者创建者
        return result;
    }

    @Override
    public String deleteCollect(JSONObject request) {
        if (StringUtils.isEmpty(request.getString("iIFId"))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 查询数据是否存在
        Infoform infoform = infoformMapper.selectById(request.getString("iIFId"));
        if (infoform == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        JSONObject userInfo = personinfoService.getCurUserInfo();
        String curHandlerCode = userInfo.getString("code");// 当前处理人编号
        infoform.setCIFUpdateTime(new Date());
        infoform.setCIF_cPICode_update(curHandlerCode);// 后面更换成从对象中取出
        infoform.setDataState("1");
        infoformMapper.updateById(infoform);
        return "删除成功！";
    }

    @Override
    @Transactional
    public JSONObject pubCollectInfo(JSONObject request) {
        request.put("cType","CollectInfo");
        Pubconfig pubconfig = pubconfigService.checkPubInfo(request);
        // 更新信息收集表单的信息（例如发布者，数据状态等）
        QueryWrapper<Infoform> infoformQueryWrapper = new QueryWrapper<>();
        infoformQueryWrapper.eq("iIFId",request.getString("iTypeId"));
        infoformQueryWrapper.eq("dataState","0");
        Infoform infoform = infoformMapper.selectOne(infoformQueryWrapper);
        if (infoform == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"表单配置信息不存在，请联系管理员处理！");
        }
        if (infoform.getCIFState().equals("发布")) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"该表单已发布！");
        }
        if (infoform.getCIFState().equals("停止")) {
            // 删除之前的待办
            QueryWrapper<Worktask> worktaskQueryWrapper = new QueryWrapper<>();
            worktaskQueryWrapper.in("type", "CollectInfo", "CollectInfoApproval");
            List<Worktask> worktaskList = worktaskMapper.selectList(worktaskQueryWrapper);
            for (Worktask wt : worktaskList) {
                wt.setDatastate("1");
            }
            worktaskService.updateBatchById(worktaskList);
        }
        infoform.setCIFPuber(pubconfig.getCPuber());// 发布者
        infoform.setCIFState("发布");// 表单状态
        infoform.setCIFPubTime(pubconfig.getDPubStartTime());// 发布时间
        infoform.setCIFPubStaff(pubconfig.getCPubToPerson());// 发布人员
        infoform.setCIFPubOrg(pubconfig.getCPubToOrg());// 发布组织
        infoform.setCIFPubFlag(pubconfig.getCPubToFlag());// 发布标签
        // 获取当前处理人信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        infoform.setCIF_cPICode_update(curUserInfo.getString("code"));// 更新修改人员
        infoform.setCIFUpdateTime(new Date());// 更新修改时间
        // 根据表单信息创建表单语句表
        String tableName = createTableOfFromConfig(request.getString("iTypeId"),infoform.getCIFTitle());
        infoform.setCIFTableName(tableName);
        infoformMapper.updateById(infoform);
        // 添加原始数据和待办
        // 获取发布人员信息
        List<JSONObject> personCodeList = organizationinfoService.getPersonCodeList(pubconfig.getCPubToPersonCode(), pubconfig.getCPubToOrgCode(), pubconfig.getCPubToFlagCode());
        // 添加数据
        infoformcreateMapper.batchInsert(tableName,personCodeList);
        // 添加待办
        List<Worktask> worktaskList = new ArrayList<>();
        for (JSONObject personInfo : personCodeList) {
            Worktask worktask = new Worktask();
            worktask.setType("CollectInfo");
            worktask.setTypeid(request.getInteger("iTypeId"));
            worktask.setCode(personInfo.getString("code"));
            worktask.setUpdatetime(new Date());
            worktask.setContent(pubconfig.getCPuber() + "发布了《" + infoform.getCIFTitle() + "》信息收集表单，请尽快完成！");
            worktaskList.add(worktask);
        }
        worktaskService.saveBatch(worktaskList);
        JSONObject result = new JSONObject();
        result.put("message","发布成功！");
        return result;
    }

    @Override
    public JSONObject deleteDataById(JSONObject request) {
        if (request==null || request.get("id") == null || request.get("iIFId") == null)
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        String tableName = getDataTableName(request.getInteger("iIFId"));
        JSONObject jsonObject = infoformcreateMapper.selectFromDataOne(tableName, request.getInteger("id"));
        String deleteSql = "update "+tableName+" set dataState = 1 where id = "+request.getInteger("id");
        infoformcreateMapper.doExcuSql(deleteSql);

        // 删除工作待办
        QueryWrapper<Worktask> worktaskQueryWrapper = new QueryWrapper<>();
        worktaskQueryWrapper.eq("type","CollectInfo");
        worktaskQueryWrapper.eq("code",jsonObject.getString("code"));
        worktaskQueryWrapper.eq("typeId",request.getInteger("iIFId"));
        worktaskQueryWrapper.eq("dataState","0");
        Worktask tarWorkTask = worktaskService.getOne(worktaskQueryWrapper);
        tarWorkTask.setDatastate("1");
        worktaskService.updateById(tarWorkTask);
        JSONObject result = new JSONObject();
        result.put("message","删除成功！");
        return result;
    }

    @Override
    public JSONObject getFromData(JSONObject request) {
        if (request == null || request.get("iIFId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<JSONObject> fromData = infoformcreateMapper.getFromDataListByTableName(getDataTableName(request.getInteger("iIFId")));
        for (JSONObject data : fromData) {
            Worktask worktask = worktaskService.getTopOneData(data.getString("code"),request.getInteger("iIFId"),"CollectInfo");
            if (worktask == null) {
                continue;
            }
            data.put("workTaskId",worktask.getId());
        }
        JSONObject result = new JSONObject();
        result.put("fromData",fromData);
        return result;
    }

    @Override
    public JSONObject clickNotify(JSONObject request) {
        if (request == null || request.get("type") == null || request.get("iIFId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean isNotifyAll = "all".equals(request.getString("type"));
        int iIFId = request.getInteger("iIFId");
        String tableName = getDataTableName(iIFId);
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        if (isNotifyAll) {
            List<JSONObject> fromDataListByTableName = infoformcreateMapper.getFromDataListByTableName(tableName);
            if (fromDataListByTableName.isEmpty()) {
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
            }
            for (JSONObject fromData : fromDataListByTableName) {
                if ("未完成".equals(fromData.getString("state"))) {
                    notifyMessage(iIFId,fromData,curUserInfo);
                }
            }
        } else {
            int id = request.getInteger("id");
            JSONObject fromData = infoformcreateMapper.selectFromDataOne(tableName,id);
            notifyMessage(iIFId,fromData,curUserInfo);
        }
        JSONObject result = new JSONObject();
        result.put("message","通知成功！");
        return result;
    }

    @Override
    public JSONObject reverseState(JSONObject request) {
        if (request == null || request.get("iIFId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Infoform infoform = infoformMapper.selectById(request.getInteger("iIFId"));
        if (infoform == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if (infoform.getCIFState().equals("发布")) {
            infoform.setCIFState("停止");
        } else if ("停止".equals(infoform.getCIFState())) {
            infoform.setCIFState("发布");
            pubconfigService.checkPubStopTime("CollectInfo",request.getInteger("iIFId"));
        } else {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"该表单尚未发布，不允许停止！");
        }
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        infoform.setCIF_cPICode_update(curUserInfo.getString("code"));
        infoform.setCIFUpdateTime(new Date());
        infoformMapper.updateById(infoform);
        JSONObject result = new JSONObject();
        result.put("message","操作成功！");
        return result;
    }

    @Override
    public JSONObject addFromDataPerson(JSONObject request) {
        if (request == null || request.get("iIFId") == null
                || request.get("codeArray") == null
                || request.get("nameArray") == null
                || request.get("orgArray") == null
        ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        JSONArray codes = request.getJSONArray("codeArray");
        JSONArray names = request.getJSONArray("nameArray");
        JSONArray orgs = request.getJSONArray("orgArray");
        // 插入数据
        insertFromDataPerson(request.getInteger("iIFId"),codes,names,orgs);

        JSONObject result = new JSONObject();
        result.put("message","操作成功！");
        return result;
    }

    @Override
    public JSONObject getPubState(JSONObject request) {
        if (request == null || request.get("iIFId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Infoform infoform = infoformMapper.selectById(request.getInteger("iIFId"));
        JSONObject result = new JSONObject();
        result.put("isPubed",!StringUtils.isEmpty(infoform.getCIFTableName()));
        result.put("isPub","发布".equals(infoform.getCIFState()));
        return result;
    }

    @Override
    public JSONObject getInfoAndPersonData(JSONObject request) {
        if (request == null || request.get("iIFId") == null || request.get("id") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String iIFId = request.getString("iIFId");
        int id = request.getInteger("id");

        // 获取用户数据
        JSONObject personFromData = infoformcreateMapper.selectFromDataOne(getDataTableName(request.getInteger("iIFId")), id);
        boolean isExits = personFromData != null;

        ArrayList<JSONObject> containers = infoformcreateMapper.getContainerInfo(iIFId);
        for (JSONObject container : containers) {
            container.put("showBorder","true".equals(container.getString("showBorder")));
            container.put("showRadius","true".equals(container.getString("showRadius")));
            // 封装容器元素
            ArrayList<JSONObject> childs = infoformcreateMapper.getChildInfo(iIFId,container.getString("iIFCId"));
            if (!childs.isEmpty()) {
                // 处理option数据
                for (JSONObject child : childs) {
                    child.put("showBorder","true".equals(child.getString("showBorder")));
                    child.put("showRadius","true".equals(child.getString("showRadius")));
                    child.put("showInnerBorder","true".equals(child.getString("showInnerBorder")));
                    child.put("isNeed","true".equals(child.getString("isNeed")));
                    if (!StringUtils.isEmpty(child.getString("options"))) {
                        child.put("options",JSONArray.parseArray(convertToJson(child.getString("options"))));
                    }
                    // 非容器、文本、等数据字段重新处理
                    String type = child.getString("type");
                    if (!(!isExits ||"text".equals(type) || "container".equals(type))) {
                        String fieldName = type+"-"+child.getString("id");
                        switch (type) {
                            case "input":
                            case "textarea":
                            case "select":
                            case "radio":{
                                if (personFromData.get(fieldName) != null) {
                                    child.put("defaultText",personFromData.get(fieldName));
                                }
                                break;
                            }

                            case "checkbox": {
                                if (personFromData.get(fieldName) != null) {
                                    String data = personFromData.getString(fieldName);
                                    child.put("defaultText",Arrays.asList(data.split(",")));
                                } else {
                                    child.put("defaultText",new ArrayList<>());
                                }
                                break;
                            }
                            case "time": {
                                if (personFromData.get(fieldName) != null) {
                                    child.put("defaultTime",personFromData.get(fieldName));
                                }
                                break;
                            }
                        }
                    }
                }
                container.put("child",childs);
            }
        }
        JSONObject result = new JSONObject();
        Infoform infoForm = infoformMapper.selectById(iIFId);
        int maxMetaId = infoformcreateMapper.getMetaId(iIFId);
        if (isExits) {
            String state = personFromData.getString("cState");
            state = "0".equals(state) ? "未完成" : "1".equals(state) ? "已完成" : "待审批";
            result.put("cState",state);
        }
        result.put("infoForm",infoForm);// 表单配置信息
        result.put("containers",containers);// 表单元素信息
        result.put("maxMetaId",maxMetaId);// 元素最大值ID
        result.put("authority",checkAuthority(infoForm));// 当前登入人是否是管理员或者创建者
        return result;
    }

    @Override
    public JSONObject saveFromPersonData(JSONObject request) {
        // 检查数据是否符合要求
        JSONObject data = checkSaveData(request);
        // 处理信息
        String filed = data.getString("filed");
        String fieldData = data.getString("fieldData");
        String tableName = getDataTableName(request.getInteger("iIFId"));
        // 是否需要添加审批
        String cState = "1";
        Pubconfig pubconfig = pubconfigService.getPubConfigByData("CollectInfo",request.getInteger("iIFId"));
        Infoform infoform = infoformMapper.selectById(request.getInteger("iIFId"));
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        if ("true".equals(pubconfig.getCIsApproval())) {
            cState = "2";
            String approvalCode = null;
            // 添加审批待办
            if ("true".equals(pubconfig.getCIsOrgManger())) {
                // 将待办添加给所属部门负责人
                // 获取部门数据
                List<String> orgs = organizationinfoService.getOrgCodesByPubConfig(pubconfig);
                if (!orgs.isEmpty()) {
                    approvalCode = organizationinfoService.selectPersonExitOrgs(curUserInfo.getString("code"),String.join(",",orgs));
                    approvalCode = infoform.getCIF_cPICode_insert();
                }
                if (approvalCode == null) {
                    approvalCode = infoform.getCIF_cPICode_insert();
                }
            }
            if (approvalCode == null) {
                approvalCode = infoform.getCIF_cPICode_insert();
            }

            Worktask worktask = new Worktask();
            worktask.setType("CollectInfoApproval");
            worktask.setTypeid(request.getInteger("iIFId"));
            worktask.setCode(approvalCode);
            worktask.setUpdatetime(new Date());
            worktask.setContent("成员"+curUserInfo.getString("name")+"已提交"+infoform.getCIFTitle()+"信息表单收集，请审批！");
            worktaskMapper.insert(worktask);
        }

        // 保存数据
        JSONObject oldData = infoformcreateMapper.selectFromDataOne(tableName, request.getInteger("id"));
        String insertSql = "insert into "+tableName+" ("+filed+"code,name,org,cState)"+" values ("+fieldData+"'"+oldData.getString("code")+"','" +
                oldData.getString("name")+"','" +
                oldData.getString("org")+"','"+cState+"')";
        infoformcreateMapper.doExcuSql(insertSql);
        // 作废之前的数据
        String deleteSql = "update "+tableName+" set dataState = '1' where id = "+request.getInteger("id");
        infoformcreateMapper.doExcuSql(deleteSql);

        // 处理工作待办
        Worktask worktask = worktaskMapper.selectById(request.getInteger("workTaskId"));
        if ("2".equals(cState)) {
            worktask.setState("完成");
        } else {
            worktask.setState("通过");
        }
        worktaskMapper.updateById(worktask);

        JSONObject result = new JSONObject();
        result.put("message","保存成功！");
        return result;
    }

    private JSONObject checkSaveData(JSONObject request) {
        if (request == null
                || request.get("containers") == null
                || request.get("workTaskId") == null
                || request.get("iIFId") == null
                || request.get("id") == null
        ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 字段
        String filed = "";
        // 数据
        String fieldData = "";

        ArrayList<HashMap<String,Object>> containerDatas = (ArrayList<HashMap<String,Object>>)request.get("containers");
        for (HashMap<String,Object> container : containerDatas) {
            ArrayList<HashMap<String,Object>> childs = (ArrayList<HashMap<String,Object>>)container.get("child");
            for (HashMap<String,Object> child : childs) {
                String type = child.get("type").toString();
                // 判断是否必填
                boolean isNeed = child.get("isNeed") != null && (boolean) child.get("isNeed");
                if (isNeed) {
                    if (child.get("defaultText") == null || StringUtils.isEmpty(child.get("defaultText").toString())) {
                        throw new BusinessException(ErrorCode.PARAMS_ERROR,child.get("title").toString()+"不允许为空!");
                    }
                }
                switch (type) {
                    case "input":
                    case "textarea":
                    {
                        // 最大字数
                        int maxLength = Integer.parseInt(child.get("maxLength").toString());
                        String content = "";
                        if (child.get("defaultText") != null) {
                            if (maxLength < child.get("defaultText").toString().length()) {
                                throw new BusinessException(ErrorCode.OPERATION_ERROR,child.get("title").toString()+"不允许超过长度"+maxLength+"!");
                            }
                            content =  child.get("defaultText").toString();
                        }

                        filed += "`"+type+"-"+child.get("id")+ "`,";
                        fieldData += "'"+content+"',";
                        break;
                    }
                    case "select":
                    case "radio": {
                        String content = "";
                        if (child.get("defaultText") != null) {
                            content =  child.get("defaultText").toString();
                        }

                        filed += "`"+type+"-"+child.get("id")+ "`,";
                        fieldData += "'"+content+"',";
                        break;
                    }
                    // 多选处理
                    case "checkbox": {
                        if (child.get("defaultText") != null) {
                            String content = child.get("defaultText").toString();
                            content = content.substring(1,content.length()-1);
                            if (content.split(",").length > Integer.valueOf(child.get("maxOption").toString())
                                    && content.split(",").length < Integer.valueOf(child.get("minOption").toString())) {
                                throw new BusinessException(ErrorCode.OPERATION_ERROR,
                                        child.get("title").toString()+"请选择"+child.get("minOption").toString()+"~"+child.get("maxOption").toString()+"个选项！");
                            }
                            filed += "`"+type+"-"+child.get("id")+ "`,";
                            fieldData += "'"+content+"',";
                        }
                        break;
                    }
                    case "time": {
                        if (child.get("defaultTime") != null) {
                            JSONObject meta = new JSONObject(child);
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            filed += "`"+type+"-"+child.get("id")+ "`,";
                            fieldData += "'"+formatter.format(meta.getDate("defaultTime"))+"',";
                        }
                        break;
                    }
                }
            }
        }
        JSONObject result = new JSONObject();
        result.put("filed",filed);
        result.put("fieldData",fieldData);
        return result;
    }

    @Override
    public JSONObject getFromDataId(JSONObject request) {
        if (request == null || request.get("iIFId") == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 判断该表单收集是否已经停止
        Infoform infoform = infoformMapper.selectById(request.getInteger("iIFId"));
        if ("停止".equals(infoform.getCIFState())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"该收集表单已停止！");
        }

        // 查询当前人的记录信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        JSONObject data = infoformcreateMapper.getPersonData(infoform.getCIFTableName(), curUserInfo.getString("code"));
        if (data == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        JSONObject result = new JSONObject();
        result.put("id",data.getInteger("id"));
        return result;
    }

    private void insertFromDataPerson(Integer iIFId, JSONArray codes, JSONArray names, JSONArray orgs) {
        // 查看是否是发布中
        Infoform infoform = infoformMapper.selectById(iIFId);
        Pubconfig pubconfig = pubconfigService.getPubConfigByData("CollectInfo",iIFId);
        String tableName = getDataTableName(iIFId);
        if (infoform == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (!"发布".equals(infoform.getCIFState())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"该表单尚未发布，不允许新增！");
        }
        List<JSONObject> fromDataList = infoformcreateMapper.getFromDataListByTableName(tableName);
        HashSet<String> exitsCodes = new HashSet<>();// 当前表单存在的人员编码
        for (JSONObject data : fromDataList) {
            exitsCodes.add(data.getString("code"));
        }

        String tempSql = "insert into "+tableName+" ( code,name,org ) values (";
        String insertSql = tempSql;
        for (int i = 0; i < codes.size(); i++) {
            if (exitsCodes.contains(codes.get(i).toString())) {
                continue;
            }
            insertSql += "'"+codes.get(i)+"','"+names.get(i)+"','"+orgs.get(i)+"'";
            // 进行插入
            insertSql += ") ";
            infoformcreateMapper.doExcuSql(insertSql);
            insertSql = tempSql;

            // 添加工作待办
            Worktask worktask = new Worktask();
            worktask.setType("CollectInfo");
            worktask.setTypeid(iIFId);
            worktask.setCode((String)codes.get(i));
            worktask.setUpdatetime(new Date());
            worktask.setContent(pubconfig.getCPuber() + "发布了《" + infoform.getCIFTitle() + "》信息收集表单，请尽管完成！");
            worktaskService.save(worktask);
        }
    }

    private void notifyMessage(int iIFId, JSONObject fromData, JSONObject curUserInfo) {
        Infoform infoform = infoformMapper.selectById(iIFId);
        Workmessage workmessage = new Workmessage();
        workmessage.setType("collectInfo");
        workmessage.setTypeId(iIFId);
        workmessage.setPersonCode(fromData.getString("code"));
        workmessage.setPersonName(fromData.getString("name"));
        workmessage.setContent("管理员通知您尽管完成"+infoform.getCIFTitle()+"信息表单填写！");
        workmessage.setHandler(curUserInfo.getString("name"));
        workmessage.setHandleTime(new Date());
        workmessageMapper.insert(workmessage);
    }

    /**
     * 获取表名
     * @param iIFId 信息收集配置表id
     */
    private String getDataTableName(Integer iIFId) {
        Infoform infoform = infoformMapper.selectById(iIFId);
        if (infoform == null || StringUtils.isEmpty(infoform.getCIFTableName()))
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        return infoform.getCIFTableName();
    }

    /**
     * 创建表单数据表
     * @param iIFId 表单配置ID
     */
    private String createTableOfFromConfig(String iIFId,String title) {
        // 查询表单元素
        QueryWrapper<Infoformcreate> infoformcreateQueryWrapper = new QueryWrapper<>();
        infoformcreateQueryWrapper.eq("iIFC_iIFId",iIFId);
        infoformcreateQueryWrapper.ne("type","container");// 不是容器
        infoformcreateQueryWrapper.ne("type","text");// 不是文本
        List<Infoformcreate> infoformcreates = infoformcreateMapper.selectList(infoformcreateQueryWrapper);

        // 生成表名
        String tableName = generateTableName(iIFId);
        // 删除表
        infoformcreateMapper.deleteFromDataTable(tableName);
        // 创建表
        String createSql = "CREATE TABLE `"+tableName+"`  (" +
            "`id` INT NOT NULL AUTO_INCREMENT COMMENT '自增主键'," +
            "`code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '人员编号'," +
            "`name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '人员名称'," +
            "`org` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '所属部门名称'," +
            "`dCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',"+
            "`dUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',"+
            "`cState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0：未完成；1：已完成；2：待审批）'," +
            "`dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）'," ;
        for (Infoformcreate infoform: infoformcreates) {
            String type = infoform.getType();
            switch (type) {
                case "input" : {
                    createSql += "`input-"+infoform.getId()+"` varchar("+(infoform.getMaxLength()+4)+") ";
                    if (!StringUtils.isEmpty(infoform.getDefaultText())) {
                        createSql += "DEFAULT '"+infoform.getDefaultText()+"' ";
                    }
                    createSql += " COMMENT '"+infoform.getTitle()+"',";
                    break;
                }
                case "select" : {
                    createSql += "`select-"+infoform.getId()+"` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '"+infoform.getTitle()+"',";
                    break;
                }
                case "textarea" : {
                    createSql += "`textarea-"+infoform.getId()+"` varchar("+(infoform.getMaxLength()+4)+") ";
                    if (!StringUtils.isEmpty(infoform.getDefaultText())) {
                        createSql += "DEFAULT '"+infoform.getDefaultText()+"' ";
                    }
                    createSql += " COMMENT '"+infoform.getTitle()+"',";
                    break;
                }
                case "radio" : {
                    createSql += "`radio-"+infoform.getId()+"` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '"+infoform.getTitle()+"',";
                    break;
                }
                case "checkbox" : {
                    createSql += "`checkbox-"+infoform.getId()+"` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '"+infoform.getTitle()+"',";
                    break;
                }
                case "time" : {
                    createSql += "`time-"+infoform.getId()+"` datetime ";
                    if (infoform.getDefaultTime() != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = dateFormat.format(infoform.getDefaultTime());
                        createSql += " DEFAULT '"+formattedDate+"' ";
                    }
                    createSql += " COMMENT '"+infoform.getTitle()+"',";
                    break;
                }
            }
        }
        createSql += "PRIMARY KEY (`id`) USING BTREE" +
                ") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集数据表-"+title+"' ROW_FORMAT = DYNAMIC;";
        infoformcreateMapper.createFromDataTable(createSql);
        return tableName;
    }

    /**
     * 生成表名
     */
    private String generateTableName(String tableName) {
        tableName = "ci_"+tableName+"_";
        // 获取年月日
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        tableName += formattedDate;
        return tableName;
    }

    /**
     * 检查表单数据
     */
    private void checkCollectData(ArrayList<HashMap<String, Object>> containers) {
        int i =0;
        int count = 0;
        for (HashMap<String, Object> container : containers) {
            i++;
            int j = 0;
            ArrayList<HashMap<String,Object>> metas = (ArrayList<HashMap<String,Object>>)container.get("child");
            for (HashMap<String,Object> meta : metas) {
                j++;
                if ("text".equals(meta.get("type"))) {
                    continue;
                }
                if (StringUtils.isEmpty((String)meta.get("title"))) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"表单数据第"+i+"个容器的第"+j+"个元素的数据名称不允许为空！");
                }
                count++;
            }
        }
        if (count == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"表单数据至少存在一个非文本元素！");
        }
    }

    private boolean checkAuthority(Infoform infoForm) {
        // 获取当前用户编号,名称
        JSONObject userInfo = personinfoService.getCurUserInfo();
        String curHandlerCode = userInfo.getString("code");// 当前处理人编号
        String curHandler = userInfo.getString("name");// 当前处理人名称
        if (infoForm.getCIF_cPICode_insert().equals(curHandlerCode)) {
            return true;
        }
        return Arrays.stream(infoForm.getCIFManager().split(","))
                .anyMatch(element -> element.equals(curHandler));
    }
    private boolean checkAuthority(JSONObject infoForm) {
        // 获取当前用户编号,名称
        JSONObject userInfo = personinfoService.getCurUserInfo();
        String curHandlerCode = userInfo.getString("code");// 当前处理人编号
        String curHandler = userInfo.getString("name");// 当前处理人名称
        if (infoForm.getString("cIF_cPICode_insert").equals(curHandlerCode)) {
            return true;
        }
        return Arrays.stream(infoForm.getString("cIFManager").split(","))
                .anyMatch(element -> element.equals(curHandler));
    }

    /**
     * 将存入的json字符串转化为标准的格式，例如：
     *      [{id=0, content=选项一}, {id=3, content=2}, {id=4, content=3}]
     *  转化为：
     *      [{"id":0,"content":"选项一"},{"id":3,"content":"2"},{"id":4,"content":"3"}]
     * @param input
     * @return
     */
    private String convertToJson(String input) {
        // 使用正则表达式匹配键值对
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(input);

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");

        // 遍历匹配结果，构建新的JSON字符串
        while (matcher.find()) {
            String match = matcher.group(1);
            String[] keyValuePairs = match.split(",");

            jsonBuilder.append("{");

            for (String keyValuePair : keyValuePairs) {
                String[] parts = keyValuePair.split("=");

                String key = parts[0].trim();
                String value = parts[1].trim();

                jsonBuilder.append("\"").append(key).append("\":\"").append(value).append("\",");
            }

            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
            jsonBuilder.append("},");
        }
        jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}
