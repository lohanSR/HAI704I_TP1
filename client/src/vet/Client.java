package vet;

import java.lang.reflect.Proxy;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);

            Cabinet cabinet = (Cabinet) registry.lookup("Cabinet123");
            cabinet.abonner(new ObservateurClient());

            System.out.println("Liste des patients :");
            for (Animal animal : cabinet.getPatients()) {
                System.out.println("- " + animal.getNomAnimal());
            }

            System.out.println("\nRecherche de Rex :");
            Animal rex = cabinet.getPatientByName("Rex");

            if (rex != null) {
                System.out.println("Patient trouvé : " + rex.getNomAnimal());
            } else {
                System.out.println("Patient introuvable");
            }

            System.out.println("\nRecherche d'un patient inexistant :");
            Animal inconnu = cabinet.getPatientByName("Inconnu");

            if (inconnu == null) {
                System.out.println("Patient introuvable");
            }

            System.out.println("\ntest pour A5");
            System.out.println("Nombre de patients avant : " + cabinet.getPatients().size());

            cabinet.addPatient(
                    "Django",
                    "Alice",
                    "Siamois",
                    new Espece("Chat", "15 ans"),
                    "Bon"
            );

            System.out.println("Nombre de patients après : " + cabinet.getPatients().size());

            Animal nouveau = cabinet.getPatientByName("Django");

            if (nouveau != null) {
                System.out.println("Patient ajouté : " + nouveau.getNomAnimal());
                System.out.println("État de santé : "
                        + nouveau.getDossierSuivi().getEtatSante());
            } else {
                System.out.println("Patient non trouvé");
            }

            Espece chien = new Chien("Pedro", "14 ans");
            cabinet.addPatient(
                    "Pedro",
                    "M",
                    "M",
                    chien,
                    "Bon"
            );

            System.out.println("Liste des patients :");
            for (Animal animal : cabinet.getPatients()) {
                System.out.println("- " + animal.getNomAnimal());
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}
