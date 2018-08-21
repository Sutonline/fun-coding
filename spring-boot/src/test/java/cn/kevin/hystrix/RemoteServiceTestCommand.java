package cn.kevin.hystrix;

import com.netflix.hystrix.HystrixCommand;

/**
 * @author yongkang.zhang
 * created at 21/08/2018
 */
public class RemoteServiceTestCommand extends HystrixCommand<String> {

    private RemoteServiceTestSimulator remoteService;

    public RemoteServiceTestCommand(Setter setter, RemoteServiceTestSimulator remoteService) {
        super(setter);
        this.remoteService = remoteService;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("开始调用");
        return remoteService.execute();
    }
}
