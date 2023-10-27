package com.example.teamassistantbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.entity.Fileinfo;
import com.example.teamassistantbackend.service.FileinfoService;
import com.example.teamassistantbackend.mapper.FileinfoMapper;
import org.springframework.stereotype.Service;

/**
* @author huang
* @description 针对表【fileinfo(文件信息表)】的数据库操作Service实现
* @createDate 2023-10-26 23:43:40
*/
@Service
public class FileinfoServiceImpl extends ServiceImpl<FileinfoMapper, Fileinfo>
    implements FileinfoService{

}




