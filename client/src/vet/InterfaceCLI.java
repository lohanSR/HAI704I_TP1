package vet;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class InterfaceCLI {
    private LogiqueCLI logique;

    public InterfaceCLI(LogiqueCLI logique) {
        this.logique = logique;
    }

    public void demarrer() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            affichageCLI();

            try {
                int choix = Integer.parseInt(scanner.nextLine());

                if (choix == 0) {
                    break;
                }

                switch (choix) {
                    case 1:
                        this.afficherPatients();
                        break;
                    default:
                        System.out.println("Il faut choisir une option valide");
                }
            } catch (NumberFormatException e ) {
                System.out.println("Veuillez entrer un nombre");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void affichageCLI() {
        System.out.println("\n=== Cabinet Vétérinaire ===");
        System.out.println("1. Lister les patients");
        System.out.println("0. Quitter");
        System.out.print("\nChoix : ");
    }

    private void afficherPatients() {
        List<String> patients = this.logique.getPatientsNameList();

        if (patients == null) {
            System.out.println("Impossible de contacter le serveur");
            return;
        }

        System.out.println("Liste des patients :");
        for (String name : patients) {
            System.out.println("- " + name);
        }
    }
}
