package cn.kevin.multiCast;

/**
 * @author yongkang.zhang
 * created at 15/08/2018
 */
public class MulitiCastTest {

    public static void main(String[] args) {

        ApiImpl api = new ApiImpl();

        System.out.println(api instanceof IApi);
        System.out.println(api instanceof IRead);
        System.out.println(api instanceof IWrite);

        IApi iApi = (IApi) api;
        IRead iRead = (IRead) api;
        IWrite iWrite = (IWrite) api;

        ReadAndWrite readAndWrite = new ReadAndWrite();

        System.out.println(readAndWrite instanceof IApi);
        System.out.println(readAndWrite instanceof IRead);
        System.out.println(readAndWrite instanceof IWrite);

        IApi iApi2 = (IApi) readAndWrite;
        IRead iRead2 = (IRead) readAndWrite;
        IWrite iWrite2 = (IWrite) readAndWrite;

    }

}
