package com.liminghua.api.configuration.aop;

import com.liminghua.api.system.entity.Api;
import com.liminghua.api.system.mapper.ApiMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName RequestCheckAspect
 * @Description: 切点
 * @Author LiMinghua
 * @Date 2020/9/17
 * @Version V1.0
 **/
@Component
@Aspect
public class RequestCheckAspect {
    @Autowired
    ApiMapper apiMapper;

    @Pointcut(value = "@annotation(com.liminghua.api.configuration.aop.RequestCheck)")
    public void access() {
    }

    @Before("access()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("-aop 进入切点方法前启动-" + new Date());
    }

    @Around("@annotation(requestCheck)")
    public Object around(ProceedingJoinPoint pjp, RequestCheck requestCheck) throws Throwable {
        System.out.println("-aop 环绕阶段-" + new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String requestUrl = request.getRequestURL().toString();
        String requestCheckValue = requestCheck.value();
        System.out.println("-aop 环绕阶段- requestCheckValue:"+requestCheckValue);
        //查找端口号后MappingUrl开始的位置
        int index = requestUrl.indexOf(":8666")+5;
        //截取字符串获得接口请求路径
        String urlParam = requestUrl.substring(index);
        //根据请求路径查询
        Api api = apiMapper.getInterfaceByUrl(urlParam);
        if(api.isApiSwitch()){
            System.out.println("-aop 环绕阶段- 接口为开启状态");
            System.out.println("-aop 环绕阶段- 返回接口方法继续执行");
            return pjp.proceed();
        }
        System.out.println("-aop 环绕阶段- 接口为关闭状态");
        return "接口关闭，请联系系统管理员";
    }

    @After("access()")
    public void after(JoinPoint joinPoint) {
        System.out.println("-aop 切点方法执行完毕-" + new Date());
    }

}
