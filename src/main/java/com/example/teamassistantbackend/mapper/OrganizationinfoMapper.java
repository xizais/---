package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Organizationinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author huang
* @description 针对表【organizationinfo(组织信息表)】的数据库操作Mapper
* @createDate 2023-10-26 23:43:40
* @Entity generator.entity.Organizationinfo
*/
@Mapper
public interface OrganizationinfoMapper extends BaseMapper<Organizationinfo> {

    List<JSONObject> getOrgList(String perCode);// 获取部门列表信息：自己或全部的

    String getManagerName(String orgManagerInfo);

    List<JSONObject> getMyPerArray(@Param(value = "curPerCode") String curPerCode, @Param(value = "curOrgCode") String curOrgCode, @Param(value = "allData") String allData);
}




