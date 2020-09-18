package com.liminghua.api.sysa.entity;

/**
 * 汽车实体类
 * @ClassName car
 * @Description: 实体类
 * @Author LiMinghua
 * @Date 2020/5/29
 * @Version V1.0
 **/
public class Car {
    private int id;
    private String name;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
