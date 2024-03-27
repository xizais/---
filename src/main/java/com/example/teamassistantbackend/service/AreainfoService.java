package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Areainfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author huang
* @description 针对表【areainfo】的数据库操作Service
* @createDate 2024-03-27 14:01:25
*/
public interface AreainfoService extends IService<Areainfo> {

    JSONObject addAreaInfo(JSONObject request);

    JSONObject deleteAreaInfo(JSONObject request);

    JSONObject getAreaInfoList(JSONObject request);

    JSONObject getAreaInfo(JSONObject request);
}
