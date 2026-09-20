package vet;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CabinetImpl extends UnicastRemoteObject implements Cabinet {
    private List<Animal> patients;

    public CabinetImpl(List<Animal> patients) throws RemoteException {
        this.patients = patients;
    }

    @Override
    public List<Animal> getPatients() throws RemoteException {
        return this.patients;
    }

    @Override
    public Animal getPatientByName(String name) throws RemoteException {
        if (name == null) {
            return null;
        }

        for (Animal animal : this.patients) {
            if (name.equalsIgnoreCase(animal.getNomAnimal())) {
                return animal;
            }
        }
        return null;
    }
}
