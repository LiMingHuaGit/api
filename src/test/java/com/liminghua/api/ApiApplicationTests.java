package com.liminghua.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//让测试在Spring容器环境下执行。如测试类中无此注解，将导致service,dao等自动注入失败。
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
