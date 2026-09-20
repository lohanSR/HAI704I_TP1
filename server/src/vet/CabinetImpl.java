package vet;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
    public void addPatient(String nom, String nomMaitre, String race, Espece espece, String etatSante) throws RemoteException {
        DossierSuivi dossierSuivi = new DossierSuiviImpl(etatSante, new ArrayList<String>());
        Animal animal = new AnimalImpl(nom, nomMaitre, race, espece, dossierSuivi);

        this.patients.add(animal);
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
