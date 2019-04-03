package cn.kevin.sharding;

import com.zaxxer.hikari.HikariDataSource;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.ShardingStrategyConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author yongkang.zhang
 * created at 21/11/2018
 */
/*@Configuration*/
public class ShardingConfig {

    /*@Bean*/
    public DataSource dataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.setDefaultDataSourceName("ds0");
        shardingRuleConfiguration.getTableRuleConfigs().add(userTableRuleConfiguration());
        return ShardingDataSourceFactory.createDataSource(Collections.singletonMap("ds0", hikariDataSource()),
                shardingRuleConfiguration, new HashMap<>(0), new Properties());
    }

    private TableRuleConfiguration userTableRuleConfiguration() {
        ShardingStrategyConfiguration shardingStrategyConfiguration = new StandardShardingStrategyConfiguration("id", StandardShardingStrategy.class.getName());
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
        tableRuleConfiguration.setLogicTable("user");
        tableRuleConfiguration.setActualDataNodes("ds0.user_${0..1}");
        tableRuleConfiguration.setTableShardingStrategyConfig(shardingStrategyConfiguration);

        return tableRuleConfiguration;
    }



    private DataSource hikariDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?allowMultiQueries=true");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        return dataSource;
    }


}
