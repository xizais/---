package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Noticemanager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author huang
* @description 针对表【noticemanager】的数据库操作Mapper
* @createDate 2024-03-26 16:24:43
* @Entity generator.entity.Noticemanager
*/
public interface NoticemanagerMapper extends BaseMapper<Noticemanager> {

    List<JSONObject> getInfoList(String code);

    List<JSONObject> getPersonData(@Param(value = "data") JSONObject request);

    int getPersonDataDontMount(@Param(value = "data") JSONObject request);
}




