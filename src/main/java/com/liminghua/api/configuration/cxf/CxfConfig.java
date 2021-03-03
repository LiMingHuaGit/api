package com.liminghua.api.configuration.cxf;

import com.liminghua.api.sysa.webservice.CarWebService;


import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;

/**
 * @author LiMinghua
 */
@Configuration
public class CxfConfig {

    @Autowired
    private CarWebService carWebService;

    @Bean
    public ServletRegistrationBean<CXFServlet> servletRegistrationBean() {
        return new ServletRegistrationBean<>(new CXFServlet(),"/soap/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }


    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(),carWebService);
        endpoint.publish("/api");
        return endpoint;
    }
}
