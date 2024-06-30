package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Chatconfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author huang
* @description 针对表【chatconfig】的数据库操作Mapper
* @createDate 2024-04-19 20:42:46
* @Entity generator.entity.Chatconfig
*/
public interface ChatconfigMapper extends BaseMapper<Chatconfig> {

    List<JSONObject> getMessageConfig(String code);
}




