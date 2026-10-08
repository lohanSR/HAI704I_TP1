package vet;

import java.lang.reflect.Proxy;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            String host = args.length > 0 ? args[0] : "localhost";
            int port = args.length > 1 ? Integer.parseInt(args[1]) : 1099;

            Registry registry = LocateRegistry.getRegistry(host, port);
            Cabinet cabinet = (Cabinet) registry.lookup("Cabinet123");
            //cabinet.abonner(new ObservateurClient());

            InterfaceCLI interfaceCLI = new InterfaceCLI(new LogiqueCLI(cabinet));
            interfaceCLI.demarrer();


        } catch (Exception e) {
            System.err.println("Impossible de se connecter au cabinet : " + e.getMessage());
        }
    }
}
