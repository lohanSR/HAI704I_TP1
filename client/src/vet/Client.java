package vet;

import java.lang.reflect.Proxy;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            Animal stub = (Animal) registry.lookup("Tigre");

            System.out.println("classe du stub : " + stub.getClass().getName());
            System.out.println("proxy dynamique ? " + Proxy.isProxyClass(stub.getClass()));

            System.out.println("Nom de l'animal : " + stub.getNomAnimal());
            System.out.println("Nom du maitre de l'animal : " + stub.getNomMaitre());
            System.out.println("Nom de la race : " + stub.getRace());
            System.out.println("Nom de l'espece avant changement : " + stub.getEspece().getNom());

            Espece espece = stub.getEspece();
            espece.setNom("Chat");
            System.out.println("Nom de l'espece local : " + espece.getNom());
            System.out.println("Nom de l'espece serveur : " + stub.getEspece().getNom());
            System.out.println("Hashcode Client : " + System.identityHashCode(espece));
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}
