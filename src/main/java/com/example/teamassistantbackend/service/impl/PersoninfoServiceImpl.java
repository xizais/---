package com.example.teamassistantbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.entity.Personinfo;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.mapper.PersoninfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author huang
* @description 针对表【personinfo(人员信息表)】的数据库操作Service实现
* @createDate 2023-10-26 23:43:40
*/
@Service
public class PersoninfoServiceImpl extends ServiceImpl<PersoninfoMapper, Personinfo>
    implements PersoninfoService{

    @Resource
    PersoninfoMapper personinfoMapper;
    @Override
    public boolean addPersonInfo(Personinfo personinfo) {
        return personinfoMapper.insert(personinfo) > 0 ;
    }
}




