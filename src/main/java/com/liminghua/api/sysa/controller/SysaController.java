package com.liminghua.api.sysa.controller;

import com.liminghua.api.configuration.aop.RequestCheck;
import com.liminghua.api.sysa.service.impl.CarServiceImpl;
import com.liminghua.api.sysb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * A系统Controller
 * @ClassName sysaController
 * @Author LiMinghua
 * @Date 2020/6/1
 * @Version V1.0
 **/
@RestController
@RequestMapping(value = "/sysa")
public class SysaController {

    @Autowired
    CarServiceImpl carService;
    /**
     * 查询所有汽车信息
     * @param:
     * @return: Json
     * @auther: LiMinghua
     * @date: 2020/6/2 8:51
     */
    @RequestCheck("getAllCar接口")
    @RequestMapping(value = "car/query/all", method = RequestMethod.GET)
    public String getAllCar(){
        return carService.getAllCar();
    }



}
