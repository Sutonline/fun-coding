package cn.kevin.lock;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yongkang.zhang
 * date 2022/7/24
 */
public class LockTest {

    @Test
    @SneakyThrows
    public void conditionDemoTwoThreads() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ConditionDemo conditionDemo = new ConditionDemo();

        executorService.submit(conditionDemo::produce);
        executorService.submit(conditionDemo::consume);

        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }

    @Test
    @SneakyThrows
    public void conditionDemoFourThreads() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        ConditionDemo conditionDemo = new ConditionDemo();

        executorService.submit(conditionDemo::produce);
        executorService.submit(conditionDemo::consume);
        executorService.submit(conditionDemo::printStart);
        executorService.submit(conditionDemo::printEnd);

        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }

    @Test
    @SneakyThrows
    public void synchronizedDemoTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();

        executorService.submit(synchronizedDemo::produce);
        executorService.submit(synchronizedDemo::consume);

        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }

    @Test
    public void park() {
        System.out.println("1111");
        LockSupport.park(this);
        System.out.println("2222");
    }
}
