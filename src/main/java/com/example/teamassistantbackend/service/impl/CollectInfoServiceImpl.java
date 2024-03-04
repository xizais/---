package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.entity.Fileinfo;
import com.example.teamassistantbackend.mapper.FileinfoMapper;
import com.example.teamassistantbackend.service.CollectInfoService;
import com.example.teamassistantbackend.service.FileinfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CollectInfoServiceImpl implements CollectInfoService {
    @Override
    public JSONObject saveCollectInfo(ArrayList<JSONObject> containers,boolean isAdd) {
        JSONObject result = new JSONObject();
        // 检查数据

        // 保存数据

        for (JSONObject fromData : containers) {
            // 保存表单容器

            // 保存表单元素

            // 保存表单配置（权限、数据管理等）

            // 创建表结构，并且将表结构存储到表中

        }
        // 返回结果
        return result;
    }
}
