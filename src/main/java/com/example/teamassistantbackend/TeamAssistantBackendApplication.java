package com.example.teamassistantbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.teamassistantbackend.mapper")
public class TeamAssistantBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamAssistantBackendApplication.class, args);
    }

}
