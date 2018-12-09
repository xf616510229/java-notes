package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISayHello extends Remote {

    String sayHello(String name) throws RemoteException;
    
}
