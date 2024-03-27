package com.example.teamassistantbackend.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.service.NoticemanagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: xizai
 * @Date: 2024-03-26
 */
@Controller
@RequestMapping("/NoticeManager")
public class NoticeManagerController {
    @Resource
    NoticemanagerService noticemanagerService;

    /**
     * 通知管理 处理
     */
    @PostMapping("/saveNoticeManager")
    @ResponseBody
    public BaseResponse<JSONObject> saveNoticeManager(@RequestBody JSONObject request){
        return ResultUtils.success(noticemanagerService.saveNoticeInfo(request));
    }

    /**
     * 作废通知管理 处理
     */
    @PostMapping("/deleteNotifyInfo")
    @ResponseBody
    public BaseResponse<JSONObject> deleteNotifyInfo(@RequestBody JSONObject request){
        return ResultUtils.success(noticemanagerService.deleteNotifyInfo(request));
    }

    /**
     * 获取信息展示列表
     */
    @PostMapping("/getNotifyInfoList")
    @ResponseBody
    public BaseResponse<JSONObject> getNotifyInfoList(){
        return ResultUtils.success(noticemanagerService.getInfoList());
    }

    /**
     * 获取当页数据
     */
    @PostMapping("/getNotifyInfo")
    @ResponseBody
    public BaseResponse<JSONObject> getNotifyInfo(@RequestBody JSONObject request){
        return ResultUtils.success(noticemanagerService.getInfo(request));
    }

    /**
     * 发布通知
     */
    @PostMapping("/pubNotify")
    @ResponseBody
    public BaseResponse<JSONObject> pubNotify(@RequestBody JSONObject request){
        return ResultUtils.success(noticemanagerService.pubNotify(request));
    }

    /**
     * 获取用户详情信息
     */
    @PostMapping("/getNotifyPersonDataList")
    @ResponseBody
    public BaseResponse<JSONObject> getNotifyPersonDataList(@RequestBody JSONObject request){
        return ResultUtils.success(noticemanagerService.getDataList(request));
    }
}
