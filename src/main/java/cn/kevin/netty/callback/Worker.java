package cn.kevin.netty.callback;

/**
 * 简单的callback示例</br>
 * 把callback注入真正执行的部分，在各种情况下执行回调
 *
 * created by yongkang.zhang
 * added at 2018/4/10
 */
public class Worker {

    public void doWork() {
        Fetcher fetcher = new MyFetcher(new Data(1, 0));
        fetcher.fetchData(new FetchCallback() {
            @Override
            public void onData(Data data) throws Exception {
                System.out.println("Data received: " + data);
            }

            @Override
            public void onError(Throwable cause) {
                System.out.println("An error occur: " + cause.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork();
    }


}
