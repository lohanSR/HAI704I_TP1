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

            return "\nNom : " + patient.getNomAnimal() + "\nMaitre : " + patient.getNomMaitre() + "\nRace : " +
                    patient.getRace() + "\nEspèce : " + patient.getEspece().getNom();
        } catch (RemoteException e) {
            return "Impossible de contacter le serveur";
        }
    }

    public String consulterDossier(String nom) {
        try {
            DossierSuivi dossierSuivi = this.cabinet.getPatientByName(nom).getDossierSuivi();

            StringBuilder msg = new StringBuilder("\n=== Dossier de " + nom + " ==="
                    + "\nÉtat de santé : " + dossierSuivi.getEtatSante()
                    + "\nObservations :");

            for (String observation : dossierSuivi.getObservations()) {
                msg.append("\n- ").append(observation);
            }

            return msg.toString();
        } catch (RemoteException e) {
            return "Impossible de contacter le serveur";
        }
    }

    public String modifierEtatSanteDossier(String nomPatient, String etatSante) {
        try {
            DossierSuivi dossierSuivi = this.cabinet.getPatientByName(nomPatient).getDossierSuivi();

            dossierSuivi.setEtatSante(etatSante);

            return "État de santé de \"" + nomPatient + "\" mis à jour avec succès.";
        } catch (RemoteException e) {
            return "Impossible de contacter le serveur";
        }
    }

    public String ajouterObservationDossier(String nomPatient, String observation) {
        try {
            DossierSuivi dossierSuivi = this.cabinet.getPatientByName(nomPatient).getDossierSuivi();

            dossierSuivi.addObservation(observation);

            return "Observation ajoutée au dossier de \"" + nomPatient + "\" avec succès.";
        } catch (Exception e) {
            return "Impossible de contacter le serveur";
        }
    }

    public boolean patientExiste(String nom) {
        try {
            Animal patient = this.cabinet.getPatientByName(nom);

            return patient != null;
        } catch (RemoteException e) {
            return false;
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
