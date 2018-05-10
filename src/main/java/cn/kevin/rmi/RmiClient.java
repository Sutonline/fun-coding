package cn.kevin.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost");
        RemoteHelloWorld helloworld = (RemoteHelloWorld) registry.lookup("helloworld");
        String s = helloworld.sayHell();
        System.out.println(s);
    }
}
