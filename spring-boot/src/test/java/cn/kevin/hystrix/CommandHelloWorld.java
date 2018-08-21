package cn.kevin.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;

import java.util.concurrent.Future;

/**
 * @author yongkang.zhang
 * created at 21/08/2018
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "hello" + name;
    }

    public static void main(String[] args) {
        String s  = new CommandHelloWorld("Bob").execute();
        Future<String> fs = new CommandHelloWorld(("Bob")).queue();
        Observable<String> os = new CommandHelloWorld("Bob").observe();
    }
}
