package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Personinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author huang
* @description 针对表【personinfo(人员信息表)】的数据库操作Service
* @createDate 2023-10-26 23:43:40
*/
public interface PersoninfoService extends IService<Personinfo> {

    JSONObject userLogin(String loginPhone, String loginPassword, HttpServletRequest request);//用户登入
    void logout();//用户登出
    JSONObject getCurUserInfo();//获取当前用户信息

    String getPersonName(String code);// 通过人员编码获取人员名称

    JSONObject getCurPerInfo(); // 返回当前用户信息

    JSONObject getAllPerInfo(); // 获取系统所有用户信息

    JSONObject deletePerInfo(JSONObject request); // 删除用户信息

    void checkAuth(); // 鉴权
    boolean isSysManager(); // 是否系统管理员
}
