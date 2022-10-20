/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class StavkaPorudzbine extends AbstractDomainObject {

    private Porudzbina porudzbina;
    private int rbStavke;
    private int kolicinaStavke;
    private double cenaStavke;
    private Telefon telefon;

    public StavkaPorudzbine(Porudzbina porudzbina, int rbStavke, int kolicinaStavke, double cenaStavke, Telefon telefon) {
        this.porudzbina = porudzbina;
        this.rbStavke = rbStavke;
        this.kolicinaStavke = kolicinaStavke;
        this.cenaStavke = cenaStavke;
        this.telefon = telefon;
    }

    public StavkaPorudzbine() {
    }

    @Override
    public String nazivTabele() {
        return " stavkaPorudzbine ";
    }

    @Override
    public String alijas() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN PORUDZBINA POR USING (PORUDZBINAID) "
                + "JOIN TELEFON T USING (TELEFONID) "
                + "JOIN PROIZVODJAC PR ON (PR.PROIZVODJACID = T.PROIZVODJACID) "
                + "JOIN KUPAC K ON (K.KUPACID = POR.KUPACID) "
                + "JOIN PRODAVAC P ON (P.PRODAVACID = POR.PRODAVACID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Prodavac p = new Prodavac(rs.getLong("ProdavacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("PIB"), rs.getString("NazivKupca"),
                    rs.getString("Email"), rs.getString("Telefon"));

            Porudzbina por = new Porudzbina(rs.getLong("porudzbinaID"),
                    rs.getTimestamp("datumVremePorucivanja"), rs.getDate("datumDostave"),
                    rs.getDouble("ukupnaCena"), k, p, null);

            Proizvodjac pr = new Proizvodjac(rs.getLong("ProizvodjacID"),
                    rs.getString("NazivProizvodjaca"));

            Telefon t = new Telefon(rs.getLong("telefonID"), rs.getString("nazivTelefona"),
                    rs.getDouble("cena"), rs.getString("specifikacije"), pr);

            StavkaPorudzbine sp = new StavkaPorudzbine(por, rs.getInt("rbStavke"),
                    rs.getInt("kolicinaStavke"), rs.getDouble("cenaStavke"), t);

            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (porudzbinaID, rbStavke, kolicinaStavke, cenaStavke, telefonID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " porudzbinaID = " + porudzbina.getPorudzbinaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + porudzbina.getPorudzbinaID() + ", " + rbStavke + ", "
                + " " + kolicinaStavke + ", " + cenaStavke + ", " + telefon.getTelefonID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " WHERE POR.PORUDZBINAID = " + porudzbina.getPorudzbinaID();
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getKolicinaStavke() {
        return kolicinaStavke;
    }

    public void setKolicinaStavke(int kolicinaStavke) {
        this.kolicinaStavke = kolicinaStavke;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Telefon getTelefon() {
        return telefon;
    }

    public void setTelefon(Telefon telefon) {
        this.telefon = telefon;
    }

}
