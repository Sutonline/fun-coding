package cn.kevin.multiCast;

/**
 * @author yongkang.zhang
 * created at 15/08/2018
 */
public class ReadAndWrite implements IRead, IWrite {

    @Override
    public String read() {
        return "eeeee";
    }

    @Override
    public void write() {
        System.out.println("eeee");
    }
}
