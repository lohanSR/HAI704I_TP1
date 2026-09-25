package vet;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class InterfaceCLI {
    private LogiqueCLI logique;
    private Scanner scanner;

    public InterfaceCLI(LogiqueCLI logique) {
        this.logique = logique;
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        while (true) {
            affichageCLI();

            try {
                int choix = Integer.parseInt(this.scanner.nextLine());

                if (choix == 0) {
                    break;
                }

                switch (choix) {
                    case 1:
                        this.afficherPatients();
                        break;
                    case 2:
                        System.out.print("Nom du patient : ");
                        String nom = this.scanner.nextLine();
                        this.rechercherPatient(nom);
                        break;
                    case 3:
                        this.enregistrerPatient();
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
        System.out.println("2. Rechercher un patient");
        System.out.println("3. Enregistrer un nouveau patient");
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

    private void rechercherPatient(String nom) {
        System.out.println(this.logique.rechercherPatient(nom));
    }

    private void enregistrerPatient() {
        String nomAnimal, nomMaitre, nomRace, etatSante, nomEspece, esperanceVie;

        System.out.println("=== Nouveau patient ===");
        System.out.print("Nom de l'animal : ");
        nomAnimal = this.scanner.nextLine();
        System.out.print("Nom du maitre : ");
        nomMaitre = this.scanner.nextLine();
        System.out.print("Race : ");
        nomRace = this.scanner.nextLine();
        System.out.print("Espèce : ");
        nomEspece = this.scanner.nextLine();
        System.out.print("Espérance de vie moyenne : ");
        esperanceVie = this.scanner.nextLine();
        System.out.print("Etat de santé initial : ");
        etatSante = this.scanner.nextLine();

        System.out.println(this.logique.enregistrerPatient(nomAnimal, nomMaitre, nomRace, new Espece(nomEspece, esperanceVie), etatSante));
    }
}
