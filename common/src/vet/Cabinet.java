package vet;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Cabinet extends Remote {
    List<Animal> getPatients() throws RemoteException;
    Animal getPatientByName(String name) throws RemoteException;
    void addPatient(String nom, String nomMaitre, String race, Espece espece, String etatSante) throws RemoteException;
    void abonner(ObservateurConnexions o) throws RemoteException;
    void desabonner(ObservateurConnexions o) throws RemoteException;
}
