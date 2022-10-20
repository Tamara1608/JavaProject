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
import java.util.ArrayList;
import so.kupac.SOAddKupac;
import so.kupac.SODeleteKupac;
import so.kupac.SOGetAllKupac;
import so.kupac.SOUpdateKupac;
import so.login.SOLogin;
import so.porudzbina.SOAddPorudzbina;
import so.porudzbina.SODeletePorudzbina;
import so.porudzbina.SOGetAllPorudzbina;
import so.porudzbina.SOUpdatePorudzbina;
import so.prodavac.SOGetAllProdavac;
import so.proizvodjac.SOGetAllProizvodjac;
import so.stavkaPorudzbine.SOGetAllStavkaPorudzbine;
import so.telefon.SOGetAllTelefon;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Prodavac login(Prodavac prodavac) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(prodavac);
        return so.getUlogovani();
    }

    public void addKupac(Kupac kupac) throws Exception {
        (new SOAddKupac()).templateExecute(kupac);
    }
    
    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOAddPorudzbina()).templateExecute(porudzbina);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        (new SODeleteKupac()).templateExecute(kupac);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SODeletePorudzbina()).templateExecute(porudzbina);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        (new SOUpdateKupac()).templateExecute(kupac);
    }

    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOUpdatePorudzbina()).templateExecute(porudzbina);
    }

    public ArrayList<Prodavac> getAllProdavac() throws Exception {
        SOGetAllProdavac so = new SOGetAllProdavac();
        so.templateExecute(new Prodavac());
        return so.getLista();
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
        SOGetAllKupac so = new SOGetAllKupac();
        so.templateExecute(new Kupac());
        return so.getLista();
    }

    public ArrayList<Porudzbina> getAllPorudzbina() throws Exception {
        SOGetAllPorudzbina so = new SOGetAllPorudzbina();
        so.templateExecute(new Porudzbina());
        return so.getLista();
    }

    public ArrayList<Proizvodjac> getAllProizvodjac() throws Exception {
        SOGetAllProizvodjac so = new SOGetAllProizvodjac();
        so.templateExecute(new Proizvodjac());
        return so.getLista();
    }

    public ArrayList<StavkaPorudzbine> getAllStavkaPorudzbine(Porudzbina p) throws Exception {
        SOGetAllStavkaPorudzbine so = new SOGetAllStavkaPorudzbine();

        StavkaPorudzbine sp = new StavkaPorudzbine();
        sp.setPorudzbina(p);

        so.templateExecute(sp);
        return so.getLista();
    }

    public ArrayList<Telefon> getAllTelefon(Proizvodjac p) throws Exception {
        SOGetAllTelefon so = new SOGetAllTelefon();

        Telefon t = new Telefon();
        t.setProizvodjac(p);

        so.templateExecute(t);
        return so.getLista();
    }

}
