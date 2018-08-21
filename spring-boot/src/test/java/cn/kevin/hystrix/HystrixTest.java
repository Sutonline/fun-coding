package cn.kevin.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.Test;

/**
 * @author yongkang.zhang
 * created at 21/08/2018
 */
public class HystrixTest {

    @Test
    public void givenSvcTimeoutOf100AndDefaultSettings_whenRemoteSvcExecuted_thenReturnSuccess() {
        HystrixCommand.Setter config = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroup2"));

        assert new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(100)).execute().equals("Success");

    }

    @Test
    public void givenSvcTimeoutOf5000AndExecTimeoutOf10000_whenRemoteSvcExecuted_thenReturnSuccess() {

        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroup3"));
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
        commandProperties.withExecutionTimeoutInMilliseconds(5_000);
        config.andCommandPropertiesDefaults(commandProperties);

        assert new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(15_000)).execute().equals("Success");
    }

    @Test
    public void givenSvcTimeoutOf500AndExecTimeoutOf10000AndThreadPool_whenRemoteSvcExecuted_thenReturnSuccess() {
        HystrixCommand.Setter config = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupThreadPool"));

        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
        commandProperties.withExecutionTimeoutInMilliseconds(10_000);
        config.andCommandPropertiesDefaults(commandProperties);
        config.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
            .withMaxQueueSize(10)
            .withCoreSize(3)
            .withQueueSizeRejectionThreshold(10)
        );

        assert new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute().equals("Success");
    }

    @Test
    public void givenCircuitBreakerSetup_whenRemoteSvcCmdExecuted_thenReturnSuccess() throws InterruptedException {
        HystrixCommand.Setter config = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupCircuitBreaker"));

        HystrixCommandProperties.Setter properties = HystrixCommandProperties.Setter();
        properties.withExecutionTimeoutInMilliseconds(1000);
        properties.withCircuitBreakerSleepWindowInMilliseconds(4000);
        properties.withExecutionIsolationStrategy(
                HystrixCommandProperties.ExecutionIsolationStrategy.THREAD
        );
        properties.withCircuitBreakerEnabled(true);
        properties.withCircuitBreakerRequestVolumeThreshold(1);

        config.andCommandPropertiesDefaults(properties);
        config.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
            .withMaxQueueSize(1)
            .withCoreSize(1)
            .withQueueSizeRejectionThreshold(1));

        assert this.invokeRemoteService(config, 10_000) == null;
        assert this.invokeRemoteService(config, 10_000) == null;
        assert this.invokeRemoteService(config, 10_000) == null;

        Thread.sleep(5000);

        assert new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute().equals("Success");
        assert new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute().equals("Success");
        assert new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute().equals("Success");
    }


    private String invokeRemoteService(HystrixCommand.Setter config, int timeout)
            throws InterruptedException {

        String response = null;

        try {
            response = new RemoteServiceTestCommand(config,
                    new RemoteServiceTestSimulator(timeout)).execute();
        } catch (HystrixRuntimeException ex) {
            System.out.println("ex = " + ex);
        }

        return response;
    }
}
