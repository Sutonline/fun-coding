package cn.kevin.netty.callback;

import lombok.AllArgsConstructor;

/**
 *
 * created by yongkang.zhang
 * added at 2018/4/10
 */
@AllArgsConstructor
public class Data {

    private int m;
    private int n;

    @Override
    public String toString() {
        int r = n / m;
        return n + "/" + m + " = " + r;
    }
}
