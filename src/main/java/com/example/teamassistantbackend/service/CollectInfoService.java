package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public interface CollectInfoService {
    public JSONObject saveCollectInfo(ArrayList<HashMap<String,Object>> containers, boolean isAdd, int iIFId, String title);

    public JSONObject getInfoList(JSONObject request);// 获取表单列表信息

    public JSONObject getInfo(String iIFId);// 根据表单配置ID获取表单信息

    public String deleteCollect(JSONObject request);// 删除（更新状态为删除）表单

    public JSONObject pubCollectInfo(JSONObject request);
}
