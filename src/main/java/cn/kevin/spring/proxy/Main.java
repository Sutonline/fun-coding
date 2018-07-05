package cn.kevin.spring.proxy;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author yongkang.zhang
 * created at 05/07/2018
 */
public class Main {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new RetryAdvice());
        factory.setExposeProxy(true);

        Pojo pojo = (Pojo) factory.getProxy();
        pojo.foo();
    }
}
