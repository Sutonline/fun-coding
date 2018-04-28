package cn.kevin.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        RemoteHelloWorld helloWorld = new RemoteHelloWorldImpl();
        RemoteHelloWorld stub = (RemoteHelloWorld) UnicastRemoteObject.exportObject(helloWorld, 9999);
        LocateRegistry.createRegistry(1099);
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("helloworld", stub);
        System.out.println("绑定成功");
    }
}
