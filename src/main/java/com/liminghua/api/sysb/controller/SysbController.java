package com.liminghua.api.sysb.controller;

import com.liminghua.api.configuration.aop.RequestCheck;
import com.liminghua.api.sysa.service.impl.CarServiceImpl;
import com.liminghua.api.sysb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * B系统Controller
 * @ClassName sysbController
 * @Author LiMinghua
 * @Date 2020/6/1
 * @Version V1.0
 **/
@RestController
@RequestMapping(value = "/sysb")
public class SysbController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 查询所有用户信息
     * @param:
     * @return:
     * @auther: LiMinghua
     * @date: 2020/6/2 8:51
     */
    @RequestCheck("getAllUser接口")
    @RequestMapping(value = "user/query/all", method = RequestMethod.GET)
    public String getAllUser(){
        return userService.getAllUser();
    }



}
