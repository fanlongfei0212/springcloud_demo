package com.learn.fly.consumerserver.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import druid.DruidProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author:Fly
 * @Date:Create in 2019/6/7 下午4:19
 * @Description: 链接池配置
 * @Modified:
 */
@Configuration
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidProperties druidPropertiesInit(){

        return new DruidProperties();
    }

    /**
     * Druid
     */
    @Bean
    public DataSource dataSource(DruidProperties druidProperties) {

        return druidProperties.druidConfig(new DruidDataSource());
    }
}
