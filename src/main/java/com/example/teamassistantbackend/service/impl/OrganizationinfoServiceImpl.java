package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.*;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.OrgpersonlinkMapper;
import com.example.teamassistantbackend.mapper.OrgpersontagMapper;
import com.example.teamassistantbackend.mapper.PersoninfoMapper;
import com.example.teamassistantbackend.service.OrganizationinfoService;
import com.example.teamassistantbackend.mapper.OrganizationinfoMapper;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    OrganizationinfoMapper organizationinfoMapper;
    @Resource
    PersoninfoMapper personinfoMapper;
    @Resource
    PersoninfoService personinfoService;
    @Value("${Variable.sysManager}")
    private String sysManager;

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
        List<Orgpersontag> orgPersonTags = new ArrayList<>();
        if (StringUtils.isNotEmpty(pubconfig.getCPubToFlagCode())) {
            orgPersonTags = orgpersontagMapper.getTagInfoById(pubconfig.getCPubToFlagCode());
        }
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

    @Override
    public JSONObject getOrgManager(String orgCode) {
        if (StringUtils.isEmpty(orgCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取组织信息
        Organizationinfo organizationinfo = organizationinfoMapper.selectById(orgCode);
        if (organizationinfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"组织信息不存在！");
        }
        // 获取第一个组织管理员编码
        String[] orgManagerArr = organizationinfo.getCOIManagerCode().split(",");
        Personinfo personinfo = new Personinfo();
        // 获取组织管理人信息
        int index = 0;
        while (orgManagerArr.length > index) {
            personinfo = personinfoMapper.selectById(orgManagerArr[index]);
            if (personinfo != null) {
                break;
            }
            index++;
        }
        if (personinfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"不存在组织负责人信息！");
        }
        JSONObject personInfo = new JSONObject();
        personInfo.put("code",personInfo.getString("cPICode"));
        personInfo.put("name",personInfo.getString("cPIName"));
        return personInfo;
    }

    @Override
    public JSONObject getOrgList(JSONObject request) {
        if (request == null || request.get("isMyOrg") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 获取个人信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();

        // 获取部门信息
        List<JSONObject> orgList = new ArrayList<>();
        orgList = organizationinfoMapper.getOrgList(request.getBoolean("isMyOrg") ? curUserInfo.getString("code") : null);
        // 获取组织负责人
        for (JSONObject data : orgList) {
            String orgManagerInfo = data.getString("cOIManagerCode");
            if (StringUtils.isNotEmpty(orgManagerInfo)) {
                data.put("cOIManagerCode",organizationinfoMapper.getManagerName(orgManagerInfo));
            }
        }

        JSONObject result = new JSONObject();
        result.put("message","查询成功！");
        result.put("orgList",orgList);
        result.put("isSysManager",personinfoService.isSysManager());
        return result;
    }

    @Override
    public JSONObject getOrgPers(JSONObject request) {
        if (request == null || request.get("cOICode") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<JSONObject> perArray = orgpersonlinkMapper.getPersonTableInfoList(request.getString("cOICode"));
        int curAmount = perArray.size();
        JSONObject result = new JSONObject();
        result.put("message","查询人员成功！");
        result.put("perArray",perArray);
        result.put("curAmount",curAmount);
        return result;
    }

    @Override
    public JSONObject getChildOrg(JSONObject request) {
        if (request == null || request.get("cOICode") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<JSONObject> orgArray = orgpersonlinkMapper.getChildOrgList(request.getString("cOICode"));
        for (JSONObject data : orgArray) {
            String orgManagerInfo = data.getString("cOIManagerCode");
            if (StringUtils.isNotEmpty(orgManagerInfo)) {
                data.put("cOIManagerCode",organizationinfoMapper.getManagerName(orgManagerInfo));
            }
        }
        JSONObject result = new JSONObject();
        result.put("message","查询人员成功！");
        result.put("orgArray",orgArray);
        return result;
    }

    @Override
    public JSONObject saveOrgInfo(JSONObject request) {
        if (request == null || (request.get("parentOrgCode") == null && request.get("cOICode") == null)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (request.get("cOIOrgCode") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"组织编码信息不允许为空！");
        }
        if (request.get("cOIOrgName") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"组织名称信息不允许为空！");
        }
        if (request.get("cOIOrgDescription") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"组织描述信息不允许为空！");
        }
        if (request.get("cOIOrgType") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"组织性质信息不允许为空！");
        }
        if (request.get("bOIIsEnable") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"是否启用信息不允许为空！");
        }
        if (request.get("bOIIsPublicVisible") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"是否公用信息不允许为空！");
        }

        // 获取当前用户信息
        JSONObject curPerInfo = personinfoService.getCurPerInfo();

        // 新增
        if (request.get("cOICode") == null) {
            // 获取父级组织信息
            Organizationinfo organizationinfo = organizationinfoMapper.selectById(request.getString("parentOrgCode"));

            // 保存当前组织
            int curIndex = organizationinfo.getIOISubLevelSequence(); // 当前父级组织的子级组织数量
            Organizationinfo curOrgInfo = new Organizationinfo();
            curOrgInfo.setCOICode(organizationinfo.getCOICode() + "-" + (++curIndex));
            curOrgInfo.setCOIOrgCode(request.getString("cOIOrgCode"));
            curOrgInfo.setCOIOrgName(request.getString("cOIOrgName"));
            curOrgInfo.setCOIOrgDescription(request.getString("cOIOrgDescription"));
            curOrgInfo.setCOIOrgType(request.getString("cOIOrgType"));
            curOrgInfo.setBOIIsPublicVisible(request.getBoolean("bOIIsEnable"));
            curOrgInfo.setIOISubLevelSequence(0);
            curOrgInfo.setDOICreationTime(new Date());
            curOrgInfo.setCOI_cPICode(curPerInfo.getString("code"));
            curOrgInfo.setCOIManagerCode(curPerInfo.getString("code"));
            curOrgInfo.setBOIIsEnable(request.getBoolean("bOIIsPublicVisible"));
            organizationinfoMapper.insert(curOrgInfo);

            // 增加组织联系
            Orgpersonlink orgpersonlink = new Orgpersonlink();
            orgpersonlink.setCOPL_cOICode(curOrgInfo.getCOICode());
            orgpersonlink.setCOPL_cPICode(curPerInfo.getString("code"));
            orgpersonlink.setDOPLCreationTime(new Date());
            orgpersonlink.setCOPLCreatedBy(curPerInfo.getString("code"));
            orgpersonlink.setIOPLPersonStatus(1);
            orgpersonlinkMapper.insert(orgpersonlink);

            // 更新父级组织信息
            organizationinfo.setIOISubLevelSequence(curIndex);
            organizationinfoMapper.updateById(organizationinfo);
        }
        // 更新
        else {
            // 获取当前组织信息
            Organizationinfo organizationinfo = organizationinfoMapper.selectById(request.getInteger("cOICode"));

            // 更新当前组织
            organizationinfo.setCOIOrgCode(request.getString("cOIOrgCode"));
            organizationinfo.setCOIOrgName(request.getString("cOIOrgName"));
            organizationinfo.setCOIOrgDescription(request.getString("cOIOrgDescription"));
            organizationinfo.setCOIOrgType(request.getString("cOIOrgType"));
            organizationinfo.setBOIIsPublicVisible(request.getBoolean("bOIIsEnable"));
            organizationinfo.setBOIIsEnable(request.getBoolean("bOIIsPublicVisible"));
            organizationinfoMapper.updateById(organizationinfo);
        }

        JSONObject result = new JSONObject();
        result.put("message","子组织处理成功！");
        return result;
    }

    @Override
    public JSONObject getMyPer(JSONObject request) {
        JSONObject curPerInfo = personinfoService.getCurPerInfo();

        String allData = curPerInfo.getString("code").equals(sysManager) ? "all" : null; // 系统管理员

        List<JSONObject> myPerArray = organizationinfoMapper.getMyPerArray(curPerInfo.getString("code"),request.getString("curOrgCode"),allData);
        if (myPerArray == null || myPerArray.isEmpty()) {
            myPerArray = new ArrayList<>();
        }
        JSONObject result = new JSONObject();
        result.put("myPerArray",myPerArray);
        return result;
    }

    @Override
    public JSONObject outOrgPer(JSONObject request) {
        // 删除组织联系
        QueryWrapper<Orgpersonlink> orgpersonlinkQueryWrapper = new QueryWrapper<>();
        orgpersonlinkQueryWrapper.eq("cOPL_cOICode",request.getString("curOrgCode"));
        orgpersonlinkQueryWrapper.eq("cOPL_cPICode",request.getString("curPerCode"));
        Orgpersonlink orgpersonlink = orgpersonlinkMapper.selectOne(orgpersonlinkQueryWrapper);
        if (orgpersonlink == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        orgpersonlink.setIOPLPersonStatus(2);
        orgpersonlinkMapper.updateOrgLinkInfo(orgpersonlink);
        JSONObject result = new JSONObject();
        result.put("message","退出成功！");
        return result;
    }

    @Override
    public JSONObject addNewOrgPer(JSONObject request) {
        JSONObject curPerInfo = personinfoService.getCurPerInfo();
        // 新建组织联系（判断是否存在）
        QueryWrapper<Orgpersonlink> orgpersonlinkQueryWrapper = new QueryWrapper<>();
        orgpersonlinkQueryWrapper.eq("cOPL_cOICode",request.getString("curOrgCode"));
        orgpersonlinkQueryWrapper.eq("cOPL_cPICode",request.getString("curPerCode"));
        Orgpersonlink orgpersonlink = orgpersonlinkMapper.selectOne(orgpersonlinkQueryWrapper);
        if (orgpersonlink == null) {
            orgpersonlink = new Orgpersonlink();
            orgpersonlink.setCOPLCreatedBy(curPerInfo.getString("code"));
            orgpersonlink.setDOPLCreationTime(new Date());
            orgpersonlink.setIOPLPersonStatus(1);
            orgpersonlink.setCOPL_cOICode(request.getString("curOrgCode"));
            orgpersonlink.setCOPL_cPICode(request.getString("curPerCode"));
            orgpersonlinkMapper.insert(orgpersonlink);
        } else {
            orgpersonlink.setIOPLPersonStatus(1);
            orgpersonlinkMapper.updateOrgLinkInfo(orgpersonlink);
        }

        JSONObject result = new JSONObject();
        result.put("message","新增成功！");
        return result;
    }

    private List<String> getPersonCodesByFlag(String flagCodes) {
        if (StringUtils.isEmpty(flagCodes)) {
            return new ArrayList<>();
        }
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




