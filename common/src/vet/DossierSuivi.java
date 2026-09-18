package vet;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface DossierSuivi extends Remote {
    String getEtatSante() throws RemoteException;
    List<String> getObservations() throws RemoteException;
    void addObservation(String observation) throws RemoteException;
    void setEtatSante(String etatSante) throws RemoteException;
}
