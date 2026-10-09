package vet;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {

    public static final int PORT = 1099;

    public static void main(String[] args) {
        try {
            DossierSuivi dossierTigrou = new DossierSuiviImpl("Bon", new ArrayList<>(Arrays.asList("Fracture patte arrière", "Opération", "Contrôle post-opératoire")));
            Animal tigrou = new AnimalImpl("Tigrou", "Pedro", "Européen", new Espece("Chat", "15 ans"), dossierTigrou);

            DossierSuivi dossierRex = new DossierSuiviImpl("À surveiller", new ArrayList<>(Arrays.asList("Vaccination annuelle", "Douleur à la patte")));
            Animal rex = new AnimalImpl("Rex", "Julie", "Berger allemand", new Espece("Chien", "12 ans"), dossierRex);

            DossierSuivi dossierNemo = new DossierSuiviImpl("Très bon", new ArrayList<>(Arrays.asList("Contrôle général")));
            Animal nemo = new AnimalImpl("Nemo", "Lucas", "Poisson rouge", new Espece("Poisson", "10 ans"), dossierNemo);

            List<Animal> patients = new ArrayList<>();
            patients.add(tigrou);
            patients.add(rex);
            patients.add(nemo);
            Cabinet cabinet = new CabinetImpl(patients);

            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.rebind("Cabinet123", cabinet);

            System.out.println("Server ready, port " + PORT + ")");
            //System.out.println("Hashcode Serveur : " + System.identityHashCode(tigrou.getEspece()));
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}
