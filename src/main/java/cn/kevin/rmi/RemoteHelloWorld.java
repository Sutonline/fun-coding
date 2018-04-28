package cn.kevin.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteHelloWorld extends Remote {

    String sayHell() throws RemoteException;
}
