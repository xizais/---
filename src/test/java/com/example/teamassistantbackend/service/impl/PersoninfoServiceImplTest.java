package com.example.teamassistantbackend.service.impl;
import java.util.Date;

import com.example.teamassistantbackend.entity.Personinfo;
import com.example.teamassistantbackend.service.PersoninfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersoninfoServiceImplTest {
    @Resource
    PersoninfoService personinfoService;
    @Test
    void addPersonInfo() {
        Personinfo personinfo = new Personinfo();
        personinfo.setCPICode("201549221");
        personinfo.setCPIName("希仔");
        personinfo.setCPIGender("男");
        personinfo.setCPIHometown("");
        personinfo.setCPIContactNumber("13829377437");
        personinfo.setCPIEmail("");
        personinfo.setCPIIDType("");
        personinfo.setCPIIDNumber("");
        personinfo.setCPIIdentity("学生");
        personinfo.setCPI_cOICode("1-1-1-2");
        personinfo.setDPICreateTime(new Date());
        personinfo.setCPIPassword("123456");

        if (personinfoService.addPersonInfo(personinfo))
            System.out.println("插入成功！");
        else
            System.out.println("插入失败！");
    }
}