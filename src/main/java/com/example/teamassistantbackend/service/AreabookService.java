package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Areabook;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author huang
* @description 针对表【areabook】的数据库操作Service
* @createDate 2024-03-27 14:01:25
*/
public interface AreabookService extends IService<Areabook> {

    JSONObject addAreaBook(JSONObject request);

    JSONObject deleteAreaBook(JSONObject request);

    JSONObject getAreaBookList();

    JSONObject getAreaBookInfo(JSONObject request);
}
