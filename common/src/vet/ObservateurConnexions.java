package vet;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObservateurConnexions extends Remote {
    void alerteRecue(String message) throws RemoteException;
}
