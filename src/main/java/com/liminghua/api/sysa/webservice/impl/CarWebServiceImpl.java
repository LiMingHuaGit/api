package com.liminghua.api.sysa.webservice.impl;

import com.liminghua.api.sysa.service.impl.CarServiceImpl;
import com.liminghua.api.sysa.webservice.CarWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;


/**
 * @ClassName CarWebServiceImpl
 * @Author LiMinghua
 * @Date 2021/2/18
 * @Version V1.0
 **/
@Component
public class CarWebServiceImpl implements CarWebService {
    @Autowired
    CarServiceImpl carService;

    @Override
    public String getAllCar() {
        return carService.getAllCar();
    }
}
