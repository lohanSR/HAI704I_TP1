package vet;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static final int PORT = 1099;

    public static void main(String[] args) {
        try {
            Animal tigre = new AnimalImpl("Tigrou", "Pedro", "race", new Espece("espece", "10 ans"));

            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.rebind("Tigre", tigre);

            System.out.println("Server ready, port " + PORT + ")");
            System.out.println("Hashcode Serveur : " + System.identityHashCode(tigre.getEspece()));
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}
