package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.entity.Organizationinfo;
import com.example.teamassistantbackend.entity.Orgpersontag;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.example.teamassistantbackend.mapper.OrgpersonlinkMapper;
import com.example.teamassistantbackend.mapper.OrgpersontagMapper;
import com.example.teamassistantbackend.mapper.PersoninfoMapper;
import com.example.teamassistantbackend.service.OrganizationinfoService;
import com.example.teamassistantbackend.mapper.OrganizationinfoMapper;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author huang
* @description 针对表【organizationinfo(组织信息表)】的数据库操作Service实现
* @createDate 2023-10-26 23:43:40
*/
@Service
public class OrganizationinfoServiceImpl extends ServiceImpl<OrganizationinfoMapper, Organizationinfo>
    implements OrganizationinfoService{

    @Resource
    OrgpersonlinkMapper orgpersonlinkMapper;
    @Resource
    OrgpersontagMapper orgpersontagMapper;
    @Resource
    PersoninfoMapper personinfoMapper;

    @Override
    public List<JSONObject> getPersonCodeList(String personCodes, String orgCodes, String flagCodes) {
            // 获取人员code
            List<String> personCodeList = new ArrayList<>();
            if (!StringUtils.isEmpty(personCodes)) {
                personCodeList.addAll(getPersonCodes(personCodes));
            }
            // 获取组织code
            if (!StringUtils.isEmpty(orgCodes)) {
                personCodeList.addAll(getPersonCodesByOrgCodes(orgCodes));
            }
            // 获取标签code->获取人员、组织code
            if (!StringUtils.isEmpty(flagCodes)) {
                personCodeList.addAll(getPersonCodesByFlag(flagCodes));
            }
            personCodeList = personCodeList.stream()
                    .distinct()
                    .collect(Collectors.toList());// 去重返回
        List<JSONObject> dataList = new ArrayList<>();
        dataList = personinfoMapper.getUserCodeAndName(
                        personCodeList.stream()
                            .map(code -> "'" + code + "'")
                            .collect(Collectors.joining(",")));
        return dataList;
    }

    @Override
    public List<String> getOrgCodesByPubConfig(Pubconfig pubconfig) {
        List<String> result = Arrays.asList(pubconfig.getCPubToOrgCode().split(","));
        List<Orgpersontag> orgPersonTags = orgpersontagMapper.getTagInfoById(pubconfig.getCPubToFlagCode());
        for (Orgpersontag data : orgPersonTags) {
            result.addAll(Arrays.asList(data.getCOPTIncludedOrgId().split(",")));
        }
        return result.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public String selectPersonExitOrgs(String personCode, String orgs) {
        List<JSONObject> datas = orgpersonlinkMapper.selectPersonByPersonCodeAndOrgCodes(personCode,orgs);
        if (datas.isEmpty()) {
            return null;
        }
        return datas.get(0).getString("cOPL_cOICode");
    }

    private List<String> getPersonCodesByFlag(String flagCodes) {
        flagCodes = Arrays.stream(flagCodes.split(","))
                .map(String::trim)
                .map(code -> "'" + code + "'")
                .collect(Collectors.joining(", "));
        List<Orgpersontag> orgPersonTags = orgpersontagMapper.getTagInfoById(flagCodes);
        List<String> result = new ArrayList<>();
        for (Orgpersontag opt : orgPersonTags) {
            // 人员
            if (!StringUtils.isEmpty(opt.getCOPTIncludedPersonCode())) {
                result.addAll(getPersonCodes(opt.getCOPTIncludedPersonCode()));
            }
            // 组织
            if (!StringUtils.isEmpty(opt.getCOPTIncludedOrgId())) {
                result.addAll(getPersonCodesByOrgCodes(opt.getCOPTIncludedOrgId()));
            }
        }
        return result;
    }

    private List<String> getPersonCodesByOrgCodes(String orgCodes) {
        orgCodes = Arrays.stream(orgCodes.split(","))
                .map(String::trim)
                .map(code -> "'" + code + "'")
                .collect(Collectors.joining(", "));
        return orgpersonlinkMapper.getPersonCodeByOrgCode(orgCodes);
    }

    private List<String> getPersonCodes(String personCodes){
        return Arrays.stream(personCodes.split(","))
                .map(String::trim)
                .distinct()
                .collect(Collectors.toList());
    }
}




