package cn.kevin.jdk9;

/**
 * private interface demo
 * created by yongkang.zhang
 * added at 2018/1/3
 */
public interface PrivateInterfaceDemo {


    default String getDefaultName() {
        return "default";
    }

    String getNameSelf();

}
