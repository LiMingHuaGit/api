package com.liminghua.api.configuration.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 自定义sysa的DataSouce和SqlSessionFactory、SqlSessionTemplate
 * @ClassName MybatisConfigA
 * @Author LiMinghua
 * @Date 2020/6/1
 * @Version V1.0
 **/
@Configuration
@MapperScan(basePackages = "com.liminghua.api.sysa.mapper",sqlSessionFactoryRef = "sqlSessionFactoryA",sqlSessionTemplateRef = "sqlSessionTemplateA")
public class MybatisConfigA {

    /**
     * Bean(name = "dataSourceA") 注入到这个容器
     * ConfigurationProperties(prefix = "spring.datasource.sysa") 表示取application.properties配置文件中的前缀
     * Primary 表示优先注入
     * @auther: LiMinghua
     * @date: 2020/6/2 8:55
     */
    @Bean(name = "dataSourceA")
    @ConfigurationProperties(prefix = "spring.datasource.sysa")
    @Primary
    public DataSource dataSourceA() {
        return DataSourceBuilder.create().build();
    }

    /**
     * A系统SqlSessionFactory
     * @auther: LiMinghua
     * @date: 2020/6/2 8:58
     */
    @Bean(name = "sqlSessionFactoryA")
    @Primary
    SqlSessionFactory sqlSessionFactoryA(@Qualifier("dataSourceA") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * A系统SqlSessionTemplate
     * @auther: LiMinghua
     * @date: 2020/6/2 8:59
     */
    @Bean(name = "sqlSessionTemplateA")
    @Primary
    SqlSessionTemplate sqlSessionTemplateA(@Qualifier("sqlSessionFactoryA")  SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
