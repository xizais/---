package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Chatconfig;
import com.example.teamassistantbackend.entity.Chatinfo;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.ChatconfigMapper;
import com.example.teamassistantbackend.mapper.ChatinfoMapper;
import com.example.teamassistantbackend.service.ChatconfigService;
import com.example.teamassistantbackend.service.ChatinfoService;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author huang
* @description 针对表【chatinfo】的数据库操作Service实现
* @createDate 2024-04-19 20:42:46
*/
@Service
public class ChatinfoServiceImpl extends ServiceImpl<ChatinfoMapper, Chatinfo>
    implements ChatinfoService {

    @Resource
    PersoninfoService personinfoService;
    @Resource
    ChatinfoMapper chatinfoMapper;
    @Resource
    ChatconfigMapper chatconfigMapper;
    @Override
    public JSONObject getMyMessage() {
        // 获取个人信息
        JSONObject curPerInfo = personinfoService.getCurPerInfo();
        // 获取聊天配置
        List<JSONObject> messageList = chatconfigMapper.getMessageConfig(curPerInfo.getString("code"));
        // 获取信息内容
        for (JSONObject data : messageList) {
            String selfName = personinfoService.getPersonName(data.getString("cCCSelfCode"));
            String otherName = personinfoService.getPersonName(data.getString("cCCOtherCode"));
            List<JSONObject> infoList = chatinfoMapper.getMessageInfo(data.getString("cCCSelfCode"),data.getString("cCCOtherCode"),selfName,otherName);
            if (infoList == null || infoList.isEmpty()) {
                infoList = new ArrayList<>();
            }
            data.put("content",infoList);
            data.put("num",infoList.size());
        }
        JSONObject result = new JSONObject();
        result.put("messageList",messageList);

        return result;
    }

    @Override
    public JSONObject saveMessage(JSONObject request) {
        if (request == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (request.get("content") == null || StringUtils.isEmpty(request.getString("content"))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"发送信息不允许为空！");
        }
        if (request.getString("content").length() > 500) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"发送信息过长！");
        }
        // 更新id的时间
        Chatconfig configInfo = chatconfigMapper.selectById(request.getString("id"));
        configInfo.setDUpdateTime(new Date());
        chatconfigMapper.updateById(configInfo);

        // 新增数据
        Chatinfo chatinfo = new Chatinfo();
        chatinfo.setCCISelfCode(request.getString("selfCode"));
        chatinfo.setCCIOtherCode(request.getString("otherCod"));
        chatinfo.setCCIContent(request.getString("content"));
        chatinfo.setDCISendTime(new Date());
        chatinfoMapper.insert(chatinfo);

        JSONObject result = new JSONObject();
        result.put("message","发送成功！");
        result.put("id",chatinfo.getCCIId());
        return result;
    }
}




