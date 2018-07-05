package cn.kevin.spring.proxy;

/**
 * @author yongkang.zhang
 * created at 05/07/2018
 */
public class SimplePojo implements Pojo {

    @Override
    public void foo() {
        // this next method invocation is a direct call on the 'this' reference
        this.bar();
    }

    @Override
    public void bar() {
        System.out.println("some logic");
    }
}
