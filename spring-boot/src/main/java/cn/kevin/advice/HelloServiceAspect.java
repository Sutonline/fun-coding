package cn.kevin.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yongkang.zhang
 * created at 20/11/2018
 */
@Aspect
@Component
public class HelloServiceAspect {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Around("execution(* cn.kevin.service..*(..))")
    public Object invoke(ProceedingJoinPoint jp) throws Throwable {
        executorService.submit(() -> {
            try {
                return jp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        });
        return "异步执行先返回";
    }
}
