package com.example.teamassistantbackend.service.impl;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.teamassistantbackend.entity.Infoform;
import com.example.teamassistantbackend.entity.Infoformcreate;
import com.example.teamassistantbackend.mapper.InfoformMapper;
import com.example.teamassistantbackend.mapper.InfoformcreateMapper;
import com.example.teamassistantbackend.service.CollectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class CollectInfoServiceImpl implements CollectInfoService {
    @Resource
    InfoformMapper infoformMapper;// 表单配置
    @Resource
    InfoformcreateMapper infoformcreateMapper;// 表单创建

    @Override
    public JSONObject saveCollectInfo(ArrayList<JSONObject> containers,boolean isAdd,int iIFId) {
        JSONObject result = new JSONObject();
        // 1. 检查数据


        // 2. 数据处理
        // 2.2 创建配置表
        String curHandler = "111";// 当前处理人
        Infoform infoform = new Infoform();
        if (!isAdd) {
            infoform = infoformMapper.selectById(iIFId);
            // 删除原有的表单数据
            QueryWrapper<Infoformcreate> infoformcreateQueryWrapper = new QueryWrapper<>();
            infoformcreateQueryWrapper.eq("iIFC_iIFId",iIFId);
            infoformcreateMapper.delete(infoformcreateQueryWrapper);
        }

        infoform.setCIF_cPICode_update(curHandler);// 修改者
        infoform.setCIFUpdateTime(new Date());// 修改时间

        if (isAdd) {
            infoform.setCIF_cPICode_insert(curHandler);// 创建者
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
        for (JSONObject fromData : containers) {
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
            containerForm.setType(fromData.getString("type"));
            infoformcreateMapper.insert(containerForm);
            containerId = containerForm.getIIFCId();

            // 2.3.2 保存容器内元素
            ArrayList<JSONObject> metas = (ArrayList<JSONObject>)fromData.get("child");
            for (JSONObject meta : metas) {
                Infoformcreate metaForm = new Infoformcreate();
                metaForm.setIIFC_iIFId(iIFId);
                metaForm.setId(meta.getInteger("id"));
                metaForm.setContainerId(containerId);
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
                metaForm.setOPTIONS(meta.getString("options"));
                metaForm.setDefaultTime(meta.getDate("defaultTime"));
                metaForm.setIsNeed(meta.getString("isNeed"));
                metaForm.setType(meta.getString("type"));
                infoformcreateMapper.insert(metaForm);
            }
        }

        // 3. 返回结果
        return result;
    }
}
