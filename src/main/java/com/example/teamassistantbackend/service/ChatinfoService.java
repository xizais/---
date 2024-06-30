package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Chatinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author huang
* @description 针对表【chatinfo】的数据库操作Service
* @createDate 2024-04-19 20:42:46
*/
public interface ChatinfoService extends IService<Chatinfo> {

    JSONObject getMyMessage();

    JSONObject saveMessage(JSONObject request);
}
