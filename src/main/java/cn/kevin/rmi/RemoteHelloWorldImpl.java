package cn.kevin.rmi;

import java.rmi.RemoteException;

public class RemoteHelloWorldImpl implements RemoteHelloWorld {

    @Override
    public String sayHell() throws RemoteException {
        return "Hello World, i am from stub";
    }
}
