package com.liminghua.api.sysb.entity;

import com.arturmkrtchyan.sizeof4j.SizeOf;

/**
 * @ClassName User
 * @Description: 用户表实体
 * @Author LiMinghua
 * @Date 2020/5/7
 * @Version V1.0
 **/
public class User {
    private String userId;
    private String userName;
    private String userPwd;


    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
