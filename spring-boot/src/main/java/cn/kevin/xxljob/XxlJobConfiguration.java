package cn.kevin.xxljob;

import com.xxl.job.core.executor.XxlJobExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongkang.zhang
 * created at 17/09/2018
 */
@Configuration
public class XxlJobConfiguration {

    private XxlJobConfig config;

    public XxlJobConfiguration(XxlJobConfig config) {
        this.config = config;
    }

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
        XxlJobExecutor executor = new XxlJobExecutor();
        executor.setAdminAddresses(config.getAdminAddress());
        executor.setAppName(config.getAppname());
        executor.setPort(config.getPort());

        return executor;
    }
}
