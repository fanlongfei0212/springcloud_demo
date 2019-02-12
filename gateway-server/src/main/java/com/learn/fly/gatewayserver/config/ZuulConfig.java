package com.learn.fly.gatewayserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:Fly
 * @Date:Create in 2019/2/13 上午12:08
 * @Description: 动态路由配置
 * @Modified:
 */
@Configuration
public class ZuulConfig {

    /**
     *@Author:Fly Created in 2019/2/13 上午12:09
     *@Description: 配置动态路由
     */
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties(){

        return new ZuulProperties();
    }
}
