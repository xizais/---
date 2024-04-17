package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Organizationinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.teamassistantbackend.entity.Pubconfig;

import java.util.List;

/**
* @author huang
* @description 针对表【organizationinfo(组织信息表)】的数据库操作Service
* @createDate 2023-10-26 23:43:40
*/
public interface OrganizationinfoService extends IService<Organizationinfo> {
    public List<JSONObject> getPersonCodeList(String personCodes, String orgCodes, String flagCodes); // 根据发布配置的发布人员、发布组织、发布标签查询所有人员的人员编码
    public List<String> getOrgCodesByPubConfig(Pubconfig pubconfig);// 通过发布配置获取部门编号

    String selectPersonExitOrgs(String personCode, String orgs);// 查询这个人员编码是否存在对应的组织中，并且返回第一个所属的组织的编码（默认为null）

    JSONObject getOrgManager(String orgCode);// 根据组织编码获取组织负责人信息

    JSONObject getOrgList(JSONObject request);

    JSONObject getOrgPers(JSONObject request);

    JSONObject getChildOrg(JSONObject request);

    JSONObject saveOrgInfo(JSONObject request);

    JSONObject getMyPer(JSONObject request);

    JSONObject outOrgPer(JSONObject request);

    JSONObject addNewOrgPer(JSONObject request);
}
