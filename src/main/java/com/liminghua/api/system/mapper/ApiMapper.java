package com.liminghua.api.system.mapper;

import com.liminghua.api.system.entity.Api;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ApiMapper
 * @Author LiMinghua
 * @Date 2020/6/2
 * @Version V1.0
 **/
@Mapper
@Component
public interface ApiMapper {
    /**
     * 查询数据库中所有接口信息
     * @return: List<Api>
     * @auther: LiMinghua
     * @date: 2020/6/2 21:54
     */
    public List<Api> getAllInterfaceFromDb();
    /**
     * 插入新的接口数据
     * @param api 接口实体
     * @return: 成功插入行数
     * @auther: LiMinghua
     * @date: 2020/6/2 22:10
     */
    public int insertInterface(Api api);

    /**
     * 根据请求路径查询接口信息
     *
     * @Description 根据请求路径查询接口信息
     * @param url 接口请求路径
     * @return: com.liminghua.api.system.entity.Api
     * @auther: LiMinghua
     * @date: 2020/9/18 15:14
     */
    public Api getInterfaceByUrl(String url);
}
