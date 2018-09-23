package cn.kevin.xxljob;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongkang.zhang
 * created at 17/09/2018
 */
@Configuration
@ConfigurationProperties(prefix = "xxl.job.executor")
@Data
public class XxlJobConfig {

    private String adminAddress;
    private String appname;
    private String ip;
    private Integer port;
    private String logpath;
    private Integer logretentiondays;
    private String accessToken;

}
