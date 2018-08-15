package cn.kevin.multiCast;

/**
 * @author yongkang.zhang
 * created at 15/08/2018
 */
public class ApiImpl implements IApi {
    @Override
    public String read() {
        return "dddd";
    }

    @Override
    public void write() {
        System.out.println("dddd");
    }
}
