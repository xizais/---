package com.example.teamassistantbackend.mapper;

import com.example.teamassistantbackend.entity.Orgpersonlink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author huang
* @description 针对表【orgpersonlink(组织-人员关联表)】的数据库操作Mapper
* @createDate 2023-10-26 23:43:40
* @Entity generator.entity.Orgpersonlink
*/
@Mapper
public interface OrgpersonlinkMapper extends BaseMapper<Orgpersonlink> {

    List<String> getPersonCodeByOrgCode(String orgCodes);
}




