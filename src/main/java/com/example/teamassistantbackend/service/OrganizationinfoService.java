package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Organizationinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
* @author huang
* @description 针对表【organizationinfo(组织信息表)】的数据库操作Service
* @createDate 2023-10-26 23:43:40
*/
public interface OrganizationinfoService extends IService<Organizationinfo> {
    public List<JSONObject> getPersonCodeList(String personCodes, String orgCodes, String flagCodes);
}
