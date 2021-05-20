package com.augurit.water.monitorpro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.augurit.water.**.mapper"})
public class MonitorProApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorProApplication.class, args);
    }

}
