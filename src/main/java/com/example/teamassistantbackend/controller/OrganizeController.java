package com.example.teamassistantbackend.controller;

import com.example.teamassistantbackend.entity.Organizationinfo;
import com.example.teamassistantbackend.service.OrganizationinfoService;
import org.springframework.stereotype.Controller;
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
}
