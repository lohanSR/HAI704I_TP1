package vet;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DossierSuiviImpl extends UnicastRemoteObject implements DossierSuivi {
    private String etatSante;
    private List<String> observations;

    public DossierSuiviImpl(String etatSante, List<String> observations) throws RemoteException {
        this.etatSante = etatSante;
        this.observations = observations;
    }

    @Override
    public String getEtatSante() {
        return etatSante;
    }

    @Override
    public List<String> getObservations() {
        return observations;
    }

    @Override
    public void setEtatSante(String etatSante) {
        this.etatSante = etatSante;
    }

    @Override
    public void addObservation(String observation) throws RemoteException {
        this.observations.add(observation);
    }
}
