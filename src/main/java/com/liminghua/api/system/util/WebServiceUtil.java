package com.liminghua.api.system.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName getAllWebServices
 * @Author LiMinghua
 * @Date 2021/2/18
 * @Version V1.0
 **/
@Component //交给spring管理方便其他类获取该类对象
@Scope(value="singleton")//单例
public class WebServiceUtil implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public List<String> getAllMethod(){
        List<String> rs = new ArrayList<String>();
        Map<String, Object> beansWithAnnotationMap = this.applicationContext.getBeansWithAnnotation(javax.jws.WebMethod.class);
        Class<? extends Object> clazz = null;
        for(Map.Entry<String, Object> entry : beansWithAnnotationMap.entrySet()){
            //获取到实例对象的class信息
            clazz = entry.getValue().getClass();
            Class<? extends Object>  [] interfaces = clazz.getDeclaredClasses();
            for(Class<? extends Object>  aInterface : interfaces){
                //接口信息
                rs.add(aInterface.getName());
            }
        }
        return rs;
    }
}
