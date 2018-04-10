package cn.kevin.netty.callback;

/**
 *
 * created by yongkang.zhang
 * added at 2018/4/10
 */
public interface FetchCallback {
    void onData(Data data) throws Exception;
    void onError(Throwable cause);
}
