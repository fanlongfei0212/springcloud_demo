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
        
### Eureka客户端

* 添加依赖：Eureka客户端依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

* 使用【@EnableDiscoveryClient】注解在入口类中将自己的信息发送到Eureka进行管理

```java
@EnableDiscoveryClient
@SpringBootApplication
public class HelloServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloServerApplication.class, args);
	}

}
```
        
## Config配置中心

* 添加服务端依赖：配置中心服务端依赖
    
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
spring.cloud.config.server.git.username=*****
spring.cloud.config.server.git.password=*****
```

* 动态刷新

**在配置文件中配置：management.security.enabled=false关闭安全校验，然后修改Git仓库，访问：http://{配置中心地址}:{对应端口}/refresh（POST请求）进行动态刷新**

### Config客户端

* 添加客户端依赖：配置中心客户端依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

* 在bootstrap.properties中配置Config客户端

**配置内容依次为：Config服务端的地址、对应的Git仓库中的文件名称、分支、对应的环境**

```properties
spring.cloud.config.uri=http://localhost:9000/
spring.cloud.config.name=gateway
spring.cloud.config.label=develop
spring.cloud.config.profile=dev
```

* 动态刷新

**在配置文件中配置：management.security.enabled=false关闭安全校验，然后修改Git仓库，访问：http://{客户端地址}:{对应端口}/refresh（POST请求）进行动态刷新**

## FeignClient

* 添加依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

* 使用【@EnableFeignClients】注解在入口类中开启功能

```java
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServerApplication.class, args);
	}

}
```

* 服务调用

**创建接口，在接口中描述需要调用的服务提供方的Controller中的方法，在类头上添加@FeignClient注解，
并且制定调用的服务id**

```java
@FeignClient(value = "hello-server")
public interface HelloServer {

    @RequestMapping(value = "/hello/hello", method = RequestMethod.GET)
    String hello();
}
```

## 断路器，服务熔断

**使用Feign进行断路器配置，首先在配置文件中开启服务降级，然后在配置接口中的降级后的内容,
Feign默认使用客户端负载均衡，默认规则为轮询**

```properties
feign.hystrix.enabled=true
```

```java
@Component
public class HelloServerFallback implements HelloServer{


    @Override
    public String hello() {
        return "error";
    }
}

@FeignClient(value = "hello-server", fallback = HelloServerFallback.class)
public interface HelloServer {

    @RequestMapping(value = "/hello/hello", method = RequestMethod.GET)
    String hello();
}
```

## 使用Zuul进行网关动态路由配置

* 在配置中心创建zuul网关的配置信息文件

**gateway.properties、
  gateway-dev.properties、
  gateway-prod.properties、
  gateway-test.properties**
  
* 配置文件内容（根据实际情况进行配置）

```properties
zuul.routes.consumer-server.path=/consumer-server/**
zuul.routes.consumer-server.serviceId=consumer-server
```

* 添加网关依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```

* 首先将网关服务的路由配置设置为Config客户端，配置信息从Config服务端进行获取

* 在入口类添加注解【@EnableZuulProxy】，开启路由

```java
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

}
```

* 配置使用@RefreshScope将路由配置动态化

```java
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
```