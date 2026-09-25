package vet;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CabinetImpl extends UnicastRemoteObject implements Cabinet {
    private List<Animal> patients;
    private final List<ObservateurConnexions> abonnes = new CopyOnWriteArrayList<>();

    public CabinetImpl(List<Animal> patients) throws RemoteException {
        this.patients = patients;
    }

    @Override
    public List<Animal> getPatients() throws RemoteException {
        return this.patients;
    }

    @Override
    public void abonner(ObservateurConnexions o) throws RemoteException { this.abonnes.add(o); }

    @Override
    public void desabonner(ObservateurConnexions o) throws RemoteException { this.abonnes.remove(o); }

    @Override
    public void addPatient(String nom, String nomMaitre, String race, Espece espece, String etatSante) throws RemoteException {
        DossierSuivi dossierSuivi = new DossierSuiviImpl(etatSante, new ArrayList<String>());
        Animal animal = new AnimalImpl(nom, nomMaitre, race, espece, dossierSuivi);

        System.out.println("nbPatients : " + (this.patients.size() + 1));
        switch ((this.patients.size() + 1)) {
            case 4:
                this.publier(4);
                break;
            case 100:
                this.publier(100);
                break;
            case 500:
                this.publier(500);
                break;
            case 1000:
                this.publier(1000);
                break;
        }

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

    private void publier(int nbPatients) {
        for (ObservateurConnexions o : this.abonnes) {
            try {
                String m = "Le nombre de patients est de " + nbPatients;
                o.alerteRecue(m);
            } catch (RemoteException e) {
                this.abonnes.remove(o);
                System.out.println("Observateur défaillant retiré : " + e.getMessage());
            }
        }
    }
}
