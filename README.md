# SpringCloud微服务Demo项目

**项目使用Eureka服务注册发现中心（单点服务或者集群均可），使用Config配置中心作为服务的统一配置，使用
Feign进行各个服务之间的调用并且进行客户端负载均衡，使用Hystrix进行对服务的容错保护机制，使用Zull
路由进行服务网关配置**

## 环境

* JDK：1.8

* SpringBoot：1.5.9.RELEASE

* SpringCloud：Edgware.SR5

* 构建工具：Maven

## Eureka服务注册发现中心

* 添加依赖：Eureka依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

* 使用【@EnableEurekaServer】注解在入口类中启动服务注册中心

```java
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
```

* 单节点Eureka服务注册发现中心

    1. 关闭eureka.client.register-with-eureka=false、eureka.client.fetch-registry=false配置
    
    2. application.properties配置项
    
    ```properties
    #服务配置基本配置
    server.port=8080
    spring.application.name=eureka-server
    
    #配置注册中心
    #禁止向注册中心注册自己
    eureka.client.register-with-eureka=false
    #不需要检索服务
    eureka.client.fetch-registry=false
    eureka.instance.hostname=localhost
    eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka
    ```

* 高可用Eureka服务注册发现中心

    1. 配置多个Eureka服务的配置文件作为多个服务发现节点的配置，如：
        **application-peer1.properties**
        **application-peer2.properties**
    
    2. 不同实例的Eureka的配置需要注意，端口号不能相同，同时需要开启
    eureka.client.register-with-eureka=true、eureka.client.fetch-registry=true配置，
    
    3. eureka.instance.hostname的ip地址不能相同（修改host文件配置多个服务名称或使用本机ip和公网ip均可），
    并且不能使用localhost；多个Eureka实例进行相互注册
    
        **application-peer1.properties**
        
        ```properties
        #服务配置基本配置
        server.port=8080
        spring.application.name=eureka-server
        
        #配置注册中心
        #向注册中心注册自己
        eureka.client.register-with-eureka=true
        #需要检索服务
        eureka.client.fetch-registry=true
        eureka.instance.hostname=127.0.0.1
        eureka.client.serviceUrl.defaultZone=http://192.168.0.104:8081/eureka
        ```
        
        **application-peer2.properties**
                
        ```properties
        #服务配置基本配置
        server.port=8081
        spring.application.name=eureka-server
        
        #配置注册中心
        #向注册中心注册自己
        eureka.client.register-with-eureka=true
        #需要检索服务
        eureka.client.fetch-registry=true
        eureka.instance.hostname=192.168.0.104
        eureka.client.serviceUrl.defaultZone=http://127.0.0.1:8080/eureka
        ```
        
    4. 使用指定环境的方式启动多个Eureka服务注册发现中心
    
    ```zsh
    java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
    java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
    ```
    
    5. 启动完毕后，访问localhost:8080和localhost:8081两个地址，如下所示，则配置成功
    
        * 在**DS Replicas**中出现了对方的ip
        
        * 在**Instances currently registered with Eureka**中发现两个已经注册的Eureka实例
        
        * 在**在General Info**中的registered-replicas和available-replicas中出现了其他Eureka的地址
        
## Config配置中心

* 添加依赖：Eureka依赖
    
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

* 使用【@SpringBootApplication】注解在入口类中开启SpringCloud Config的配置中心服务端功能

```java
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
```

* 配置Git的相关信息，在application.properties中

    1. spring.cloud.config.server.git.uri：配置Git仓库的地址
    2. spring.cloud.config.server.git.search-paths：配置项目中的相对文件地址
    3. spring.cloud.config.server.git.username：Git用户名
    4. spring.cloud.config.server.git.password：Git密码

```properties
spring.cloud.config.server.git.uri=https://github.com/fanlongfei0212/springcloud_demo_config.git
spring.cloud.config.server.git.search-paths=zuul
spring.cloud.config.server.git.username=fanlongfei0212
spring.cloud.config.server.git.password=githubPassword0212
```