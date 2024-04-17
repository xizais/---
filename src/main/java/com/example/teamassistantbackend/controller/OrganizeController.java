package com.example.teamassistantbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.entity.Organizationinfo;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.service.OrganizationinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 组织控制层：
 *  管理组织，包括人员信息的管理
 * @author huang
 */
@Controller
@RequestMapping("/Organize")
public class OrganizeController {
    @Resource
    OrganizationinfoService organizationinfoService;

    /**
     * 新增组织
     */
    @RequestMapping("/addOrganize")
    @ResponseBody
    public Boolean addOrganize(Organizationinfo organizationinfo){
        System.out.println(organizationinfo);
        return true;
    }

    /**
     * 获取部门列表数据
     */
    @RequestMapping("/getOrgList")
    @ResponseBody
    public BaseResponse<JSONObject> getOrgList(@RequestBody JSONObject request){
        return ResultUtils.success(organizationinfoService.getOrgList(request));
    }

    /**
     * 获取部门人员数据
     */
    @RequestMapping("/getOrgPers")
    @ResponseBody
    public BaseResponse<JSONObject> getOrgPers(@RequestBody JSONObject request){
        return ResultUtils.success(organizationinfoService.getOrgPers(request));
    }

    /**
     * 获取部门子级数据
     */
    @RequestMapping("/getChildOrg")
    @ResponseBody
    public BaseResponse<JSONObject> getChildOrg(@RequestBody JSONObject request){
        return ResultUtils.success(organizationinfoService.getChildOrg(request));
    }

    /**
     * 保存部门信息数据
     */
    @RequestMapping("/saveOrgInfo")
    @ResponseBody
    public BaseResponse<JSONObject> saveOrgInfo(@RequestBody JSONObject request){
        return ResultUtils.success(organizationinfoService.saveOrgInfo(request));
    }

    /**
     * 获取我的用户信息
     */
    @RequestMapping("/getMyPer")
    @ResponseBody
    public BaseResponse<JSONObject> getMyPer(@RequestBody JSONObject request){
        return ResultUtils.success(organizationinfoService.getMyPer(request));
    }

    /**
     * 剔除组织用户
     */
    @RequestMapping("/outOrgPer")
    @ResponseBody
    public BaseResponse<JSONObject> outOrgPer(@RequestBody JSONObject request){
        return ResultUtils.success(organizationinfoService.outOrgPer(request));
    }

    /**
     * 新增组织用户
     */
    @RequestMapping("/addNewOrgPer")
    @ResponseBody
    public BaseResponse<JSONObject> addNewOrgPer(@RequestBody JSONObject request){
        return ResultUtils.success(organizationinfoService.addNewOrgPer(request));
    }


}
