package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author huang
* @description 针对表【pubconfig(发布配置表)】的数据库操作Mapper
* @createDate 2024-03-13 17:41:28
* @Entity generator.entity.Pubconfig
*/
public interface PubconfigMapper extends BaseMapper<Pubconfig> {

    JSONObject selectPubConfig(@Param(value = "data") JSONObject request);// 查询发布配置信息，根据关联表ID、类型

    List<JSONObject> getPersonsList(@Param(value = "data")JSONObject curUserInfo);

    boolean checkDataIsPub(@Param(value = "condition") String condition,@Param(value = "name") String tableName);
}




