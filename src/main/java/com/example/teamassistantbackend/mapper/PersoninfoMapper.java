package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Personinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author huang
* @description 针对表【personinfo(人员信息表)】的数据库操作Mapper
* @createDate 2023-10-26 23:43:40
* @Entity generator.entity.Personinfo
*/
@Mapper
public interface PersoninfoMapper extends BaseMapper<Personinfo> {

    Personinfo getUserInfo(@Param(value = "userInfo") JSONObject userInfo);// 获取用户信息

    List<JSONObject> getUserCodeAndName(String personCodeList);
}




