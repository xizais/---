package com.example.teamassistantbackend.mapper;

import com.example.teamassistantbackend.entity.Orgpersontag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author huang
* @description 针对表【orgpersontag(组织-人员标签表)】的数据库操作Mapper
* @createDate 2023-10-26 23:43:40
* @Entity generator.entity.Orgpersontag
*/
@Mapper
public interface OrgpersontagMapper extends BaseMapper<Orgpersontag> {

    List<Orgpersontag> getTagInfoById(String flagCodes);
}




