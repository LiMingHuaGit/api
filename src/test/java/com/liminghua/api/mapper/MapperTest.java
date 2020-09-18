package com.liminghua.api.mapper;

import cn.hutool.core.collection.CollUtil;
import com.liminghua.api.ApiApplicationTests;
import com.liminghua.api.sysa.entity.Car;
import com.liminghua.api.sysa.mapper.CarMapper;
import com.liminghua.api.sysb.mapper.UserMapper;
import com.liminghua.api.system.entity.Api;
import com.liminghua.api.system.mapper.ApiMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import com.liminghua.api.sysb.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description: 测试Mapper
 * @Author LiMinghua
 * @Date 2020/5/7
 * @Version V1.0
 **/
@Slf4j
public class MapperTest extends ApiApplicationTests {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApiMapper apiMapper;

    @Test
    public void selectAll() {
        List<User> userListB = userMapper.getAllUser();
        Assert.assertTrue(CollUtil.isNotEmpty(userListB));
        log.debug("【userListB】= {}", userListB);

        List<Car> carListA = carMapper.getAllCar();
        Assert.assertTrue(CollUtil.isNotEmpty(carListA));
        log.debug("【carListA】= {}", carListA);

        List<Api> apiList = apiMapper.getAllInterfaceFromDb();
        log.trace("【apiList】= {}", apiList);
    }
}
