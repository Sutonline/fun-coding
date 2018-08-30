package cn.kevin.elasticjob.config;

/**
 * @author yongkang.zhang
 * created at 30/08/2018
 */
/*@Configuration*/
public class SimpleJobConfig {
/*
    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Bean(name = "job1")
    public MyElasticJob job1() {
        return new MyElasticJob();
    }

    @Bean(name = "job2")
    public MyElasticJob job2() {
        return new MyElasticJob();
    }

    @Bean(initMethod = "init")
    public JobScheduler jobScheduler(@Qualifier("job1") final SimpleJob simpleJob) {

        return new SpringJobScheduler(simpleJob, regCenter,
                getLiteJobConfiguration(simpleJob.getClass(), "0/10 * * * * ?", 3, "0=aaa, 1=bbb, 2=ccc"));
    }

    @Bean(initMethod = "init")
    public JobScheduler jobScheduler2(@Qualifier("job2") final SimpleJob simpleJob) {

        return new SpringJobScheduler(simpleJob, regCenter,
                getLiteJobConfiguration(simpleJob.getClass(), "0/10 * * * * ?", 3, "0=aaa, 1=bbb, 2=ccc"));
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount, final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build(), jobClass.getCanonicalName())).overwrite(true).build();
    }*/
}
