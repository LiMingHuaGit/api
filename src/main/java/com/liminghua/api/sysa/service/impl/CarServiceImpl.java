package com.liminghua.api.sysa.service.impl;

import com.alibaba.fastjson.JSON;
import com.liminghua.api.sysa.entity.Car;
import com.liminghua.api.sysa.mapper.CarMapper;
import com.liminghua.api.sysa.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CarServiceImpl
 * @Author LiMinghua
 * @Date 2020/6/1
 * @Version V1.0
 **/
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;


    /**
     * 查询所有用户
     * @return json
     */
    @Override
    public String getAllCar() {
        List<Car> carList = carMapper.getAllCar();
        return JSON.toJSONString(carList);
    }
}
