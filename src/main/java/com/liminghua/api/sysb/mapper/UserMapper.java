package com.liminghua.api.sysb.mapper;
import com.liminghua.api.sysb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LiMinghua
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 查询所有用户
     * //@Select("select * from user")
     * @Description //查询所有用户
     * @return: java.util.List<com.liminghua.api.sysb.entity.User>
     * @auther: LiMinghua
     * @date: 2020/5/29 14:13
     */
    public List<User> getAllUser();
}
