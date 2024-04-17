package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teamassistantbackend.entity.Worktask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 希仔
* @description 针对表【worktask(工作待办表)】的数据库操作Mapper
* @createDate 2024-03-17 22:29:29
* @Entity generator.domain.Worktask
*/
public interface WorktaskMapper extends BaseMapper<Worktask> {

    List<JSONObject> getWorkInfoList(@Param("code") String code,@Param("data") JSONObject request);

    int getWorkInfoListMount(@Param("code") String code,@Param("data") JSONObject request);

    Worktask getTopOneData(@Param(value = "code") String code,@Param(value = "typeId") Integer typeId,@Param(value = "type") String type);
}




