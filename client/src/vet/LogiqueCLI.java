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

    public String rechercherPatient(String nom) {
        try {
            Animal patient = this.cabinet.getPatientByName(nom);

            if (patient == null) {
                return "Patient introuvable";
            }

            String msg = "\nNom : " + patient.getNomAnimal() + "\nMaitre : " + patient.getNomMaitre() + "\nRace : " +
                    patient.getRace() + "\nEspèce : " + patient.getEspece().getNom();
            return msg;
        } catch (RemoteException e) {
            return "Impossible de contacter le serveur";
        }
    }

    public String enregistrerPatient(String nomAnimal, String nomMaitre, String race, Espece espece, String etatSante) {
        try {
            this.cabinet.addPatient(nomAnimal, nomMaitre, race, espece, etatSante);
            return "Patient \"" + nomAnimal + "\" enregistré avec succès.";
        } catch (RemoteException e) {
            return "Impossible de contacter le serveur";
        }
    }
}
