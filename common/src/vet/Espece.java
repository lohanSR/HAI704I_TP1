package vet;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Espece implements Serializable {
    private String nom;
    private String esperanceVie;

    public Espece(String nom, String esperanceVie) {
        this.nom = nom;
        this.esperanceVie = esperanceVie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEsperanceVie(String esperanceVie) {
        this.esperanceVie = esperanceVie;
    }

    public String getNom() {
        return nom;
    }

    public String getEsperanceVie() {
        return esperanceVie;
    }
}
