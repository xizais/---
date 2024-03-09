package com.example.teamassistantbackend.service.impl;
import java.util.Arrays;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Infoform;
import com.example.teamassistantbackend.entity.Infoformcreate;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.InfoformMapper;
import com.example.teamassistantbackend.mapper.InfoformcreateMapper;
import com.example.teamassistantbackend.service.CollectInfoService;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CollectInfoServiceImpl implements CollectInfoService {
    @Resource
    InfoformMapper infoformMapper;// 表单配置
    @Resource
    InfoformcreateMapper infoformcreateMapper;// 表单创建

    @Override
    public JSONObject saveCollectInfo(ArrayList<HashMap<String,Object>> containers, boolean isAdd, int iIFId, String title) {
        JSONObject result = new JSONObject();
        // 1. 检查数据
//        checkCollectData(containers);

        // 2. 数据处理
        // 2.2 创建配置表
        String curHandlerCode = "111";// 当前处理人编号
        String curHandler = "小希";// 当前处理人名称
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
                infoformcreateMapper.insert(metaForm);
            }
        }

        // 3. 返回结果
        return result;
    }

    @Override
    public JSONObject getInfoList(JSONObject request) {
        // 获取当前用户编号
        String cPICode = "111";
        request.put("cPICode",cPICode);
        // 查询用户表单配置信息数据：用户自身的、用户管理的（按时间排序）
        ArrayList<JSONObject> infoList = infoformMapper.getFromList(request);
        JSONObject result = new JSONObject();
        result.put("infoList",infoList);
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

    private boolean checkAuthority(Infoform infoForm) {
        // 获取当前用户编号,名称
        String curCode = "111";
        String curName = "小希";
        if (infoForm.getCIF_cPICode_insert().equals(curCode)) {
            return true;
        }
        return Arrays.stream(infoForm.getCIFManager().split(","))
                .anyMatch(element -> element.equals(curName));
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
