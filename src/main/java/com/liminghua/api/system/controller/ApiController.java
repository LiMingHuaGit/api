package com.liminghua.api.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.liminghua.api.system.service.impl.ApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName ApiController
 * @Description: TODO
 * @Author LiMinghua
 * @Date 2020/6/3
 * @Version V1.0
 **/
@RestController
@RequestMapping(value = "/system")
public class ApiController {
    @Autowired
    ApiServiceImpl apiService;

    /**
     * 查询当前系统所有运行的RequestMapping
     * @return: com.alibaba.fastjson.JSONArray
     * @auther: LiMinghua
     * @date: 2020/6/3 14:15
     */
    @RequestMapping(value = "/api/query/all", method = RequestMethod.GET)
    public JSONArray getAllApi(){
        return JSONArray.parseArray(JSON.toJSONString(apiService.getAllApi()));
    }

    /**
     * 插入新的接口信息（数据库中不存在url记录的接口）
     * @return: java.lang.String
     * @auther: LiMinghua
     * @date: 2020/6/3 14:15
     */
    @RequestMapping(value = "/api/insert/new", method = RequestMethod.GET)
    public String insertApi(){
        int rs = apiService.insertApi();
        if(rs>0){
            return ">>>上传"+rs+"条新接口数据成功>>>";
        }else{
            return">>>上传失败>>>";
        }

    }

}
