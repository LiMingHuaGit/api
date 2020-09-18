package com.liminghua.api.sysb.service.impl;

import cn.hutool.Hutool;
import com.alibaba.fastjson.JSON;
import com.liminghua.api.sysb.entity.User;
import com.liminghua.api.sysb.mapper.UserMapper;
import com.liminghua.api.sysb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author LiMinghua
 * @Date 2020/5/12
 * @Version V1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String getAllUser() {
        List<User> userList = userMapper.getAllUser();
        return JSON.toJSONString(userList);

    }
}
