package com.example.teamassistantbackend.service.impl;

import com.example.teamassistantbackend.mapper.InfoformMapper;
import com.example.teamassistantbackend.service.CollectInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: xizai
 * @Date: 2024-03-06
 */
@SpringBootTest
public class CollectInfoServiceImplTest {
    @Resource
    CollectInfoService collectInfoService;
    @Resource
    InfoformMapper infoformMapper;
    @Test
    void addInfoForm(){
//        collectInfoService.saveCollectInfo(null,true,1);
//        System.out.println(infoformMapper.getFromList("111"));
    }
}
