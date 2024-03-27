package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Areainfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author huang
* @description 针对表【areainfo】的数据库操作Mapper
* @createDate 2024-03-27 14:01:25
* @Entity generator.entity.Areainfo
*/
public interface AreainfoMapper extends BaseMapper<Areainfo> {

    List<JSONObject> getInfoList(@Param(value = "data") JSONObject data);

    JSONObject selectInfo(String iAIId);

    int getInfoListAmount(@Param(value = "data") JSONObject request);
}




