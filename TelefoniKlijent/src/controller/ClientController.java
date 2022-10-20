/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Kupac;
import domain.Porudzbina;
import domain.Prodavac;
import domain.Proizvodjac;
import domain.StavkaPorudzbine;
import domain.Telefon;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Prodavac login(Prodavac prodavac) throws Exception {
        return (Prodavac) sendRequest(Operation.LOGIN, prodavac);
    }

    public void addKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.ADD_KUPAC, kupac);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.ADD_PORUDZBINA, porudzbina);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.DELETE_KUPAC, kupac);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.DELETE_PORUDZBINA, porudzbina);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.UPDATE_KUPAC, kupac);
    }

    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.UPDATE_PORUDZBINA, porudzbina);
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
        return (ArrayList<Kupac>) sendRequest(Operation.GET_ALL_KUPAC, null);
    }

    public ArrayList<Porudzbina> getAllPorudzbina() throws Exception {
        return (ArrayList<Porudzbina>) sendRequest(Operation.GET_ALL_PORUDZBINA, null);
    }

    public ArrayList<Proizvodjac> getAllProizvodjac() throws Exception {
        return (ArrayList<Proizvodjac>) sendRequest(Operation.GET_ALL_PROIZVODJAC, null);
    }

    public ArrayList<Telefon> getAllTelefon(Proizvodjac p) throws Exception {
        return (ArrayList<Telefon>) sendRequest(Operation.GET_ALL_TELEFON, p);
    }

    public ArrayList<Prodavac> getAllProdavac() throws Exception {
        return (ArrayList<Prodavac>) sendRequest(Operation.GET_ALL_PRODAVAC, null);
    }

    public ArrayList<StavkaPorudzbine> getAllStavkaPorudzbine(Porudzbina p) throws Exception {
        return (ArrayList<StavkaPorudzbine>) sendRequest(Operation.GET_ALL_STAVKA_PORUDZBINE, p);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

}
