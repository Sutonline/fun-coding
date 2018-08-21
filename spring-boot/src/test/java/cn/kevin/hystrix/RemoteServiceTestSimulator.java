package cn.kevin.hystrix;

/**
 * @author yongkang.zhang
 * created at 21/08/2018
 */
public class RemoteServiceTestSimulator {

    private long wait;

    public RemoteServiceTestSimulator(long wait) {
        this.wait = wait;
    }

    String execute() throws InterruptedException {
        Thread.sleep(wait);
        return "Success";
    }
}
