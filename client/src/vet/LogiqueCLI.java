package vet;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class LogiqueCLI {
    private Cabinet cabinet;

    public LogiqueCLI(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public List<String> getPatientsNameList() {
        try {
            List<String> noms = new ArrayList<String>();
            List<Animal> patients = this.cabinet.getPatients();
            for (Animal animal : patients) {
                noms.add(animal.getNomAnimal());
            }
            return noms;
        } catch (RemoteException e) {
            return null;
        }
    }
}
