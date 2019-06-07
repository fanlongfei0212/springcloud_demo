package com.learn.fly.consumerserver.config.mybatis;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:Fly
 * @Date:Create in 2019/6/7 下午10:08
 * @Description: MyBatis Plus配置
 * @Modified:
 */
@Configuration
@MapperScan("com.learn.fly.consumerserver.repository")
public class MyBatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        return new PaginationInterceptor();
    }
}
