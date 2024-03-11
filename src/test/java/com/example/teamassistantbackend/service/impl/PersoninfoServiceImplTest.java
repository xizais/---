package com.example.teamassistantbackend.service.impl;
import java.util.Date;
import java.util.zip.CRC32;

import com.example.teamassistantbackend.entity.Personinfo;
import com.example.teamassistantbackend.mapper.PersoninfoMapper;
import com.example.teamassistantbackend.service.PersoninfoService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersoninfoServiceImplTest {
    @Resource
    PersoninfoService personinfoService;
    @Resource
    PersoninfoMapper personinfoMapper;
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
        CRC32 crc32 = new CRC32();
        crc32.update("123456".getBytes());
        String crc = "" + crc32.getValue();
        personinfo.setCPIPassword(crc);
        personinfoMapper.insert(personinfo);

    }

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Test
    public void suiCe(){
        // 设置键值对
        redisTemplate.opsForValue().set("key01", "value01");
        redisTemplate.opsForValue().set("key02", 111);
        Personinfo personinfo = new Personinfo();
        personinfo.setCPICode("1");
        personinfo.setCPIName("2");
        personinfo.setCPIGender("nan");
        personinfo.setDPIBirthDate(new Date());
        personinfo.setCPIHometown("2");
        personinfo.setCPIContactNumber("3");
        personinfo.setCPIEmail("4");
        personinfo.setCPIIDType("5");
        personinfo.setCPIIDNumber("6");
        personinfo.setCPIIdentity("2");
        personinfo.setCPI_cOICode("3");
        personinfo.setDPICreateTime(new Date());
        personinfo.setCPIPassword("4");
        personinfo.setBPIIsEnabled(false);

        redisTemplate.opsForValue().set("key03", personinfo);

        // 获取值
        String value1 = (String) redisTemplate.opsForValue().get("key01");
        int value2 = (int) redisTemplate.opsForValue().get("key02");
        Personinfo value3 = (Personinfo) redisTemplate.opsForValue().get("key03");

        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
        //移除
        redisTemplate.delete("key01");
    }
}