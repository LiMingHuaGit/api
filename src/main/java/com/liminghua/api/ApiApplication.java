package com.liminghua.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * MapperScan("com.liminghua.api")
 * MapperScan(basePackages = {"com.liminghua.api.sysA.mapper"})
 * @author LiMinghua
 * @Description //启动类
 * @param:
 * @return:
 * @date: 2020/5/29 8:46
 */
@MapperScan("com.liminghua.api")
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
