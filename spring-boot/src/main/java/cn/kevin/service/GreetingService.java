package cn.kevin.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yongkang.zhang
 * created at 21/08/2018
 */
@Service
public class GreetingService {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getGreeting(String username) {
        randomFailure();
        return "Hello, " + username;
    }

    public String defaultGreeting(String username) {
        return "default, welcome, " + username;
    }

    private void randomFailure() {
        this.atomicInteger.incrementAndGet();
        if (this.atomicInteger.get() % 3 == 0) {
            throw new RuntimeException("随机失败");
        }
    }
}
