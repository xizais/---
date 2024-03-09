package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teamassistantbackend.entity.Infoform;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
* @author huang
* @description 针对表【infoform(信息收集表单配置表)】的数据库操作Mapper
* @createDate 2024-03-04 14:09:55
* @Entity generator.entity.Infoform
*/
public interface InfoformMapper extends BaseMapper<Infoform> {

    ArrayList<JSONObject> getFromList(@Param(value = "data") JSONObject data);// 获取用户的表单配置
}




