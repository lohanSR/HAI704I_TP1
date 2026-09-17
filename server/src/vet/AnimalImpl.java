package vet;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AnimalImpl extends UnicastRemoteObject implements Animal {
    private String nomAnimal;
    private String nomMaitre;
    private String race;
    private String espece;

    public AnimalImpl(String nomAnimal, String nomMaitre, String race, String espece) throws RemoteException {
        this.nomAnimal = nomAnimal;
        this.nomMaitre = nomMaitre;
        this.race = race;
        this.espece = espece;
    }

    @Override
    public String getNomAnimal() throws RemoteException {
        return this.nomAnimal;
    }

    @Override
    public String getNomMaitre() throws RemoteException {
        return this.nomMaitre;
    }

    @Override
    public String getRace() throws RemoteException {
        return this.race;
    }

    @Override
    public String getEspece() throws RemoteException {
        return this.espece;
    }
}
