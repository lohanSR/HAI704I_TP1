package vet;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObservateurClient extends UnicastRemoteObject implements ObservateurConnexions {
    public ObservateurClient() throws RemoteException {
        super();
    }

    @Override
    public void alerteRecue(String message) throws RemoteException {
        System.out.println("[ALERTE] " + message);
    }
}
