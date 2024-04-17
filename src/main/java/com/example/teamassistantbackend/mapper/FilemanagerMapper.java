package com.example.teamassistantbackend.mapper;

import com.example.teamassistantbackend.entity.Filemanager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author huang
* @description 针对表【filemanager(文件管理表，用于存储文件相关信息)】的数据库操作Mapper
* @createDate 2024-04-08 00:05:37
* @Entity generator.entity.Filemanager
*/
public interface FilemanagerMapper extends BaseMapper<Filemanager> {

    void updateTypeId(@Param("fileDoIds") String fileDoIds,@Param("typeId") Integer typeId);
}




