package cn.kevin.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongkang.zhang
 * created at 14/08/2018
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfiguration {

    private String host;

}
