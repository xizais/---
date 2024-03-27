package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Areabook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author huang
* @description 针对表【areabook】的数据库操作Mapper
* @createDate 2024-03-27 14:01:25
* @Entity generator.entity.Areabook
*/
public interface AreabookMapper extends BaseMapper<Areabook> {

    String getDoneBookABIds(JSONObject request);
}




