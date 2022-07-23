package cn.kevin.redis;

import cn.kevin.ManApplication;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yongkang.zhang
 * created at 14/08/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManApplication.class)
@ActiveProfiles(profiles = {"dev"})
@Slf4j
public class RedisTest {

    @Autowired
    private RedisComponent redisComponent;

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Test
    public void test() {
        redisComponent.setValue("lalal", "kukikuikukiki");
        System.out.println(redisComponent.getData("lalal"));
    }

    @Test
    @SneakyThrows
    public void distributeLock() {
        String key = "lock";
        // 线程1加锁，睡眠5秒
        executorService.submit(() -> {
            UUID uuid = UUID.randomUUID();
            String result = redisComponent.setLock(key, uuid.toString());
            System.out.println("线程1加锁结果: " + result);
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                System.out.println("eeee");
                throw new RuntimeException(e);
            }
            String unlock = redisComponent.unlock(key, uuid.toString());
            System.out.println("线程1解锁结果: " + unlock);
        });
        // 线程2尝试释放锁，然后不断加锁
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            UUID uuid = UUID.randomUUID();
            // String unlock = redisComponent.unlock(key, uuid.toString());
            // System.out.println("线程2解锁结果: " + unlock);
            String result = "";
            while (!Objects.equals(result = redisComponent.setLock(key, uuid.toString()), "OK")) {
                try {
                    Thread.sleep(1000);
                    System.out.println("线程2加锁结果: " + result);
                } catch (InterruptedException e) {
                    System.out.println("eeee");
                    throw new RuntimeException(e);
                }
            }
            System.out.println("线程2加锁结果: " + result);
            String unlock = redisComponent.unlock(key, uuid.toString());
            System.out.println("线程2解锁结果: " + unlock);
        });

        executorService.awaitTermination(30 * 1000, TimeUnit.SECONDS);
    }
}
