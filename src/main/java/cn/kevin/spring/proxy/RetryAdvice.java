package cn.kevin.spring.proxy;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author yongkang.zhang
 * created at 05/07/2018
 */
public class RetryAdvice implements MethodBeforeAdvice {


    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("我是啦啦啦啦啦啦啦啦啦");
    }
}
