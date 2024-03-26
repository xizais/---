package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Noticemanager;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author huang
* @description 针对表【noticemanager】的数据库操作Service
* @createDate 2024-03-26 16:24:43
*/
public interface NoticemanagerService extends IService<Noticemanager> {

    JSONObject saveNoticeInfo(JSONObject request);

    JSONObject deleteNotifyInfo(JSONObject request);

    JSONObject getInfoList();

    JSONObject getInfo(JSONObject request);

    JSONObject pubNotify(JSONObject request);
}
