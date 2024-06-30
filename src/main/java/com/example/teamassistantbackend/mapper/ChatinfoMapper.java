package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Chatinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author huang
* @description 针对表【chatinfo】的数据库操作Mapper
* @createDate 2024-04-19 20:42:46
* @Entity generator.entity.Chatinfo
*/
public interface ChatinfoMapper extends BaseMapper<Chatinfo> {

    List<JSONObject> getMessageInfo(@Param(value = "cCCSelfCode") String cCCSelfCode,
                                    @Param(value = "cCCOtherCode") String cCCOtherCode,
                                    @Param(value = "selfName") String selfName,
                                    @Param(value = "otherName") String otherName);
}




