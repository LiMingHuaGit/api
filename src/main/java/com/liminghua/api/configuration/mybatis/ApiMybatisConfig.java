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
 * 自定义api框架的DataSouce和SqlSessionFactory、SqlSessionTemplate
 * @ClassName ApiMybatisConfig
 * @Author LiMinghua
 * @Date 2020/6/1
 * @Version V1.0
 **/
@Configuration
@MapperScan(basePackages = "com.liminghua.api.system.mapper",sqlSessionFactoryRef = "apiSqlSessionFactory",sqlSessionTemplateRef = "apiSqlSessionTemplate")
public class ApiMybatisConfig {

    /**
     * Bean(name = "apiDataSource") 注入到这个容器
     * ConfigurationProperties(prefix = "spring.datasource.api") 表示取application.yml配置文件中的前缀
     * Primary 表示优先注入
     * @auther: LiMinghua
     * @date: 2020/6/2 8:55
     */
    @Bean(name = "apiDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.api")
    public DataSource apiDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Api系统SqlSessionFactory
     * @auther: LiMinghua
     * @date: 2020/6/2 8:58
     */
    @Bean(name = "apiSqlSessionFactory")
    SqlSessionFactory apiSqlSessionFactory(@Qualifier("apiDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * Api系统SqlSessionTemplate
     * @auther: LiMinghua
     * @date: 2020/6/2 8:59
     */
    @Bean(name = "apiSqlSessionTemplate")
    SqlSessionTemplate apiSqlSessionTemplate(@Qualifier("apiSqlSessionFactory")  SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
