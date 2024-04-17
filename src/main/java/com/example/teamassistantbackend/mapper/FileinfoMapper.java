package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Fileinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
* @author huang
* @description 针对表【fileinfo(文件信息表)】的数据库操作Mapper
* @createDate 2023-10-26 23:43:40
* @Entity generator.entity.Fileinfo
*/
@Mapper
public interface FileinfoMapper extends BaseMapper<Fileinfo> {

    JSONObject getFileInfo(int fileId);

    List<JSONObject> getFileInfoList(String fileIds);

    List<JSONObject> getTableFileList(@Param(value = "data") JSONObject fileInfo);
}




