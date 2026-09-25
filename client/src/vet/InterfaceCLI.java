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
                        String nom = lireChampObligatoire("Nom du patient : ");
                        this.rechercherPatient(nom);
                        break;
                    case 3:
                        this.enregistrerPatient();
                        break;
                    case 4:
                        this.gererDossier();
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

    private void affichageCLI() {
        System.out.println("\n=== Cabinet Vétérinaire ===");
        System.out.println("1. Lister les patients");
        System.out.println("2. Rechercher un patient");
        System.out.println("3. Enregistrer un nouveau patient");
        System.out.println("4. Gérer le dossier d'un patient");
        System.out.println("0. Quitter");
        System.out.print("\nChoix : ");
    }

    private void affichageCLIDossier(String nomPatient) {
        System.out.println("\n=== Dossier de " + nomPatient + " ===");
        System.out.println("1. Consulter");
        System.out.println("2. Modifier état");
        System.out.println("3. Ajouter observation");
        System.out.println("0. Retour");
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

    private void consulterDossier(String nom) {
        System.out.println(this.logique.consulterDossier(nom));
    }

    private void modifierEtatSanteDossier(String nomPatient, String etatSante) {
        System.out.println(this.logique.modifierEtatSanteDossier(nomPatient, etatSante));
    }

    private void ajouterObservationDossier(String nomPatient, String observation) {
        System.out.println(this.logique.ajouterObservationDossier(nomPatient, observation));
    }

    private void enregistrerPatient() {
        String nomAnimal, nomMaitre, nomRace, etatSante, nomEspece, esperanceVie;

        System.out.println("=== Nouveau patient ===");
        nomAnimal = lireChampObligatoire("Nom de l'animal : ");
        nomMaitre = lireChampObligatoire("Nom du maître : ");
        nomRace = lireChampObligatoire("Race : ");
        nomEspece = lireChampObligatoire("Espèce : ");
        esperanceVie = lireChampObligatoire("Espérance de vie moyenne : ");
        etatSante = lireChampObligatoire("Etat de santé initial : ");

        System.out.println(this.logique.enregistrerPatient(nomAnimal, nomMaitre, nomRace, new Espece(nomEspece, esperanceVie), etatSante));
    }

    private void gererDossier() {
        String nomPatient = lireChampObligatoire("Nom du patient : ");

        StatutPatient statutPatient = this.logique.verifierPatient(nomPatient);
        if (statutPatient == StatutPatient.INTROUVABLE) {
            System.out.println("Patient introuvable.");
            return;
        } else if (statutPatient == StatutPatient.SERVEUR_INDISPONIBLE) {
            System.out.println("Impossible de contacter le serveur");
            return;
        }

        while (true) {
            affichageCLIDossier(nomPatient);

            try {
                int choix = Integer.parseInt(this.scanner.nextLine());

                if (choix == 0) {
                    return;
                }

                switch (choix) {
                    case 1:
                        this.consulterDossier(nomPatient);
                        break;
                    case 2:
                        String etatSante = lireChampObligatoire("Nouvel état de santé : ");
                        this.modifierEtatSanteDossier(nomPatient, etatSante);
                        break;
                    case 3:
                        String observation = lireChampObligatoire("Nouvelle observation du patient : ");
                        this.ajouterObservationDossier(nomPatient, observation);
                        break;
                    default:
                        System.out.println("Il faut choisir une option valide");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre");
            }
        }
    }

    private String lireChampObligatoire(String message) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine();

            if (saisie.isBlank()) {
                System.out.println("Ce champ est obligatoire.");
                continue;
            }

            return saisie.trim();
        }
    }
}
