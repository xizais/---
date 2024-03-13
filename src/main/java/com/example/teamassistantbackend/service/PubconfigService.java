package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author huang
* @description 针对表【pubconfig(发布配置表)】的数据库操作Service
* @createDate 2024-03-13 17:41:28
*/
public interface PubconfigService extends IService<Pubconfig> {

    BaseResponse<JSONObject> savePubConfig(JSONObject request);// 保存发版配置信息
}
