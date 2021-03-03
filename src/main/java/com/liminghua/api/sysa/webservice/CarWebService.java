package com.liminghua.api.sysa.webservice;


import javax.jws.WebMethod;
import javax.jws.WebService;


/**
 * @ClassName CarWebService
 * @Description: 车辆服务接口类
 * @Author LiMinghua
 * @Date 2021/2/18
 * @Version V1.0
 **/
@WebService(name = "CarWebService",// 暴露服务名称
targetNamespace = "http://webservice.sysa.api.liminghua.com"// 命名空间,一般是接口的包名倒序
)
public interface CarWebService {

    /**
     * 获取所有车辆
     *
     * @Description 获取所有车辆
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2021/2/18 15:00
     */
    @WebMethod(operationName = "getAllCar")
    String getAllCar();
}
