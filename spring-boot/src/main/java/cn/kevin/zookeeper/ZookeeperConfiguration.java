package cn.kevin.zookeeper;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongkang.zhang
 * created at 13/08/2018
 */
@Configuration
@ConfigurationProperties(prefix = "zookeeper")
@Data
public class ZookeeperConfiguration {

    private String host;
}
