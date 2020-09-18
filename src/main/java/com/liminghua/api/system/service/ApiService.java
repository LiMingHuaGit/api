package com.liminghua.api.system.service;
import com.liminghua.api.system.entity.Api;
import java.util.List;
import java.util.Map;

/**
 * 接口管理Service层
 * @ClassName ApiService
 * @Author LiMinghua
 * @Date 2020/6/3
 * @Version V1.0
 **/
public interface ApiService {
    /**
     * 获取所有requestMapping中的url路径
     * @return: apiurl
     * @auther: LiMinghua
     * @date: 2020/6/3 9:57
     */
    public List<Map<String, String>> getAllApi();

    /**
     * 插入新的api
     * @return: 插入条数
     * @auther: LiMinghua
     * @date: 2020/6/3 9:59
     */
    public int insertApi();

    /**
     * 从数据库中查询所有已存储的api接口
     * @return: java.util.List<com.liminghua.api.system.entity.Api>
     * @auther: LiMinghua
     * @date: 2020/6/3 13:47
     */
    public List<Api> getAllInterfaceFromDb();
}
