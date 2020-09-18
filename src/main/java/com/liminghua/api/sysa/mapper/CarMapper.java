package com.liminghua.api.sysa.mapper;
import com.liminghua.api.sysa.entity.Car;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 汽车Mapper
 * @author LiMinghua
 * @Description mapper
 * @date: 2020/5/29 16:15
 */
@Mapper
@Component
public interface CarMapper {

    /**
     * 查询所有汽车
     * //@Select("select * from car")
     * @return: 汽车List
     * @auther: LiMinghua
     * @date: 2020/5/29 16:17
     */
    public List<Car> getAllCar();
}
