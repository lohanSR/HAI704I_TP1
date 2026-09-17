package vet;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Animal extends Remote {
    String getNomAnimal() throws RemoteException;
    String getNomMaitre() throws RemoteException;
    String getRace() throws RemoteException;
    Espece getEspece() throws RemoteException;
}
