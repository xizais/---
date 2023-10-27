package com.example.teamassistantbackend.service;

import com.example.teamassistantbackend.entity.Personinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author huang
* @description 针对表【personinfo(人员信息表)】的数据库操作Service
* @createDate 2023-10-26 23:43:40
*/
public interface PersoninfoService extends IService<Personinfo> {
    //添加用户
    boolean addPersonInfo(Personinfo personinfo);
}
