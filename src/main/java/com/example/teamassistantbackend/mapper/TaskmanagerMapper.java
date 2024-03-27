package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Taskmanager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author huang
* @description 针对表【taskmanager】的数据库操作Mapper
* @createDate 2024-03-26 16:24:43
* @Entity generator.entity.Taskmanager
*/
public interface TaskmanagerMapper extends BaseMapper<Taskmanager> {

    List<JSONObject> getInfoList(@Param(value = "data") JSONObject request);

    int getInfoListAmount(@Param(value = "data") JSONObject request);
}




