package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public interface CollectInfoService {
    public JSONObject saveCollectInfo(ArrayList<JSONObject> fromDatas);
}
