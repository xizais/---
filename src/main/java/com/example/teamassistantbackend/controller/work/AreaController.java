package com.example.teamassistantbackend.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.service.AreabookService;
import com.example.teamassistantbackend.service.AreainfoService;
import com.example.teamassistantbackend.service.PersoninfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: xizai
 * @Date: 2024-03-27
 */
@Controller
@RequestMapping("/Area")
public class AreaController {
    @Resource
    AreabookService areabookService;
    @Resource
    AreainfoService areainfoService;

    /**
     * 场地信息新增/修改
     */
    @PostMapping("/addAreaInfo")
    @ResponseBody
    public BaseResponse<JSONObject> addAreaInfo(@RequestBody JSONObject request){
        return ResultUtils.success(areainfoService.addAreaInfo(request));
    }

    /**
     * 场地信息删除
     */
    @PostMapping("/deleteAreaInfo")
    @ResponseBody
    public BaseResponse<JSONObject> deleteAreaInfo(@RequestBody JSONObject request){
        return ResultUtils.success(areainfoService.deleteAreaInfo(request));
    }

    /**
     * 场地信息列表
     */
    @PostMapping("/getAreaInfoList")
    @ResponseBody
    public BaseResponse<JSONObject> getAreaInfoList(@RequestBody JSONObject request){
        return ResultUtils.success(areainfoService.getAreaInfoList(request));
    }

    /**
     * 场地信息详情
     */
    @PostMapping("/getAreaInfo")
    @ResponseBody
    public BaseResponse<JSONObject> getAreaInfo(@RequestBody JSONObject request){
        return ResultUtils.success(areainfoService.getAreaInfo(request));
    }

    /**
     * 场地预约
     */
    @PostMapping("/addAreaBook")
    @ResponseBody
    public BaseResponse<JSONObject> addAreaBook(@RequestBody JSONObject request){
        return ResultUtils.success(areabookService.addAreaBook(request));
    }

    /**
     * 场地预约信息删除
     */
    @PostMapping("/deleteAreaBook")
    @ResponseBody
    public BaseResponse<JSONObject> deleteAreaBook(@RequestBody JSONObject request){
        return ResultUtils.success(areabookService.deleteAreaBook(request));
    }

    /**
     * 场地预约信息列表
     */
    @PostMapping("/getAreaBookList")
    @ResponseBody
    public BaseResponse<JSONObject> getAreaBookList(){
        return ResultUtils.success(areabookService.getAreaBookList());
    }

    /**
     * 场地预约信息详情
     */
    @PostMapping("/getAreaBookInfo")
    @ResponseBody
    public BaseResponse<JSONObject> getAreaBookInfo(@RequestBody JSONObject request){
        return ResultUtils.success(areabookService.getAreaBookInfo(request));
    }

}
