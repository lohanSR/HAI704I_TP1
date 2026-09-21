package vet;

import java.lang.reflect.Proxy;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);

            Cabinet cabinet = (Cabinet) registry.lookup("Cabinet123");
            //cabinet.abonner(new ObservateurClient());

            InterfaceCLI interfaceCLI = new InterfaceCLI(new LogiqueCLI(cabinet));
            interfaceCLI.demarrer();


        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}
