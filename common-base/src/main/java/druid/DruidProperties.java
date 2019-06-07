package druid;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;

/**
 * @Author:Fly
 * @Date:Create in 2019/6/7 下午9:49
 * @Description: Druid配置
 * @Modified:
 */
@Data
public class DruidProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private Integer initialSize = 5;
    private Integer maxActive = 20;
    private Integer maxWait = 60000;

    public DruidDataSource druidConfig(DruidDataSource dataSource){

        DruidDataSource result = new DruidDataSource();

        result.setUrl(url);
        result.setUsername(username);
        result.setPassword(password);
        result.setDriverClassName(driverClassName);
        result.setInitialSize(initialSize);
        result.setMaxActive(maxActive);
        result.setMaxWait(maxWait);

        return result;
    }
}
