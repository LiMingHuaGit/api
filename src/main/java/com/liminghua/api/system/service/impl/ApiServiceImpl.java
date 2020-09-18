package com.liminghua.api.system.service.impl;
import com.liminghua.api.system.entity.Api;
import com.liminghua.api.system.mapper.ApiMapper;
import com.liminghua.api.system.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * 接口管理实现类
 * @ClassName ApiServiceImpl
 * @Author LiMinghua
 * @Date 2020/6/3
 * @Version V1.0
 **/
@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    ApiMapper apiMapper;

    /**
     * 返回系统中所有运行的requestMapping
     * @return: List<Map<String, String>>
     * @auther: LiMinghua
     * @date: 2020/6/3 11:26
     */
    @Override
    public List<Map<String, String>> getAllApi() {

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> requestMap = mapping.getHandlerMethods();
        //存放所有接口信息的List结构
        List<Map<String, String>> apiList = new ArrayList<Map<String, String>>();
        //遍历Map中所有的键值对
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m :  requestMap.entrySet()) {
            //存放单个接口信息的Map结构
            Map<String, String> apiMap = new HashMap<String, String>(4);
            //该类存储了RequestMaping中的属性信息-"请求匹配条件"
            RequestMappingInfo info = m.getKey();
            //控制器方法
            HandlerMethod method = m.getValue();
            //url路径
            PatternsRequestCondition p = info.getPatternsCondition();
            //存放路径
            for (String url : ((PatternsRequestCondition) p).getPatterns()) {
                apiMap.put("url", url);
            }
            //存放类名ClassName
            apiMap.put("className", method.getMethod().getDeclaringClass().getName());
            //存放方法名method
            apiMap.put("method", method.getMethod().getName());
            //该类对应RequestMaping中的httpMethod
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            //存放httpMethod-请求类型
            for (RequestMethod requestMethod : ((RequestMethodsRequestCondition) methodsCondition).getMethods()) {
                apiMap.put("type", requestMethod.toString());
            }
            //存入接口列表
            apiList.add(apiMap);
        }
        return apiList;

    }

    @Override
    public int insertApi() {
        //查询当前系统运行的所有requestMapping
        List<Map<String, String>> apilist = getAllApi();
        //查询数据库中存在的接口信息
        List<Api> dbApiList = getAllInterfaceFromDb();

        List<Integer> removeList = new ArrayList<Integer> ();
        for(int i = 0;i < apilist.size();i++){
            for (Api api : dbApiList) {
                if (api.getApiUrl().equals(apilist.get(i).get("url"))&&api.getApiMethod().equals(apilist.get(i).get("method"))) {
                    removeList.add(i);
                }
            }
        }

        int rs = 0;

        for(int i=0;i<apilist.size();i++){
            if(removeList.contains(i)){
                continue;
            }
            Api api = new Api();
            api.setApiMethod(apilist.get(i).get("method"));
            api.setApiClassName(apilist.get(i).get("className"));
            api.setApiUrl(apilist.get(i).get("url"));
            api.setApiType(apilist.get(i).get("type"));
            rs+=apiMapper.insertInterface(api);
        }
        return rs;
    }

    @Override
    public List<Api> getAllInterfaceFromDb() {
        return apiMapper.getAllInterfaceFromDb();
    }
}
