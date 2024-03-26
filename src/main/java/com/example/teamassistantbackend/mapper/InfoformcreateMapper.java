package com.example.teamassistantbackend.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.teamassistantbackend.entity.Infoformcreate;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
* @author huang
* @description 针对表【infoformcreate(信息收集表单创建信息表)】的数据库操作Mapper
* @createDate 2024-03-04 14:09:55
* @Entity generator.entity.Infoformcreate
*/
public interface InfoformcreateMapper extends BaseMapper<Infoformcreate> {

    ArrayList<JSONObject> getContainerInfo(String iIFId);// 根据表单配置ID获取表单容器信息

    ArrayList<JSONObject> getChildInfo(@Param(value = "iIFId") String iIFId,@Param(value = "iIFCId") String iIFCId);

    int getMetaId(String iIFId);

    void createFromDataTable(String createSql);
    void doExcuSql(String excuSql);// 执行sql


    void deleteFromDataTable(String tableName);

    void batchInsert(@Param(value = "tableName") String tableName, @Param(value = "personCodeList") List<JSONObject> personCodeList);

    List<JSONObject> getFromDataListByTableName(String tableName);// 获取表单数据

    JSONObject selectFromDataOne(@Param(value = "tableName")String tableName,@Param(value = "id") int id);
    JSONObject getPersonData(@Param(value = "tableName")String tableName,@Param(value = "code") String code);
}




