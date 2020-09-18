package com.liminghua.api.system.entity;

/**
 * 接口表实体类
 * @ClassName Api
 * @Author LiMinghua
 * @Date 2020/6/2
 * @Version V1.0
 **/
public class Api {

    private int id;
    private String apiName;
    private String apiUrl;
    private boolean apiSwitch;
    private String apiDescription;
    private String apiMethod;
    private String apiClassName;
    private String apiType;


    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }

    public String getApiClassName() {
        return apiClassName;
    }

    public void setApiClassName(String apiClassName) {
        this.apiClassName = apiClassName;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public boolean isApiSwitch() {
        return apiSwitch;
    }

    public void setApiSwitch(boolean apiSwitch) {
        this.apiSwitch = apiSwitch;
    }

    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }
}
