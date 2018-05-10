package cn.kevin.netty.callback;

/**
 * fetcher
 * created by yongkang.zhang
 * added at 2018/4/10
 */
public interface Fetcher {
    void fetchData(FetchCallback callback);
}
