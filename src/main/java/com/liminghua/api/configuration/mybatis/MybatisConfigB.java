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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 自定义sysb的DataSouce和SqlSessionFactory、SqlSessionTemplate
 * @ClassName MybatisConfigB
 * @Author LiMinghua
 * @Date 2020/6/1
 * @Version V1.0
 **/
@Configuration
@MapperScan(basePackages = "com.liminghua.api.sysb.mapper",sqlSessionFactoryRef = "sqlSessionFactoryB",sqlSessionTemplateRef = "sqlSessionTemplateB")
public class MybatisConfigB {

    /**
     * Bean(name = "dataSourceA") 注入到这个容器
     * ConfigurationProperties(prefix = "spring.datasource.sysa") 表示取application.properties配置文件中的前缀
     * @auther: LiMinghua
     * @date: 2020/6/2 8:55
     */
    @Bean(name = "dataSourceB")
    @ConfigurationProperties(prefix = "spring.datasource.sysb")
    public DataSource dataSourceB() {
        return DataSourceBuilder.create().build();
    }

    /**
     * A系统SqlSessionFactory
     * @auther: LiMinghua
     * @date: 2020/6/2 8:58
     */
    @Bean(name = "sqlSessionFactoryB")
    SqlSessionFactory sqlSessionFactoryB(@Qualifier("dataSourceB") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * A系统SqlSessionTemplate
     * @auther: LiMinghua
     * @date: 2020/6/2 8:59
     */
    @Bean(name = "sqlSessionTemplateB")
    SqlSessionTemplate sqlSessionTemplateB(@Qualifier("sqlSessionFactoryB")  SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
