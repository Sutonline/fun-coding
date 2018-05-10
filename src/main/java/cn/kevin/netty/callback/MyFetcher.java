package cn.kevin.netty.callback;

/**
 *
 * created by yongkang.zhang
 * added at 2018/4/10
 */
public class MyFetcher implements Fetcher {

    final Data data;

    public MyFetcher(Data data) {
        this.data = data;
    }

    @Override
    public void fetchData(FetchCallback callback) {
        try {
            callback.onData(data);
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
