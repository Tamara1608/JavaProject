/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Porudzbina extends AbstractDomainObject {

    private Long porudzbinaID;
    private Date datumVremePorucivanja;
    private Date datumDostave;
    private double ukupnaCena;
    private Kupac kupac;
    private Prodavac prodavac;
    private ArrayList<StavkaPorudzbine> stavkePorudzbine;

    public Porudzbina(Long porudzbinaID, Date datumVremePorucivanja, Date datumDostave, double ukupnaCena, Kupac kupac, Prodavac prodavac, ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.porudzbinaID = porudzbinaID;
        this.datumVremePorucivanja = datumVremePorucivanja;
        this.datumDostave = datumDostave;
        this.ukupnaCena = ukupnaCena;
        this.kupac = kupac;
        this.prodavac = prodavac;
        this.stavkePorudzbine = stavkePorudzbine;
    }

    public Porudzbina() {
    }

    @Override
    public String nazivTabele() {
        return " porudzbina ";
    }

    @Override
    public String alijas() {
        return " por ";
    }

    @Override
    public String join() {
        return " JOIN KUPAC K ON (K.KUPACID = POR.KUPACID) "
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

            lista.add(por);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVremePorucivanja, datumDostave, ukupnaCena, kupacID, prodavacID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " porudzbinaID = " + porudzbinaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVremePorucivanja.getTime()) + "', "
                + "'" + new java.sql.Date(datumDostave.getTime()) + "', "
                + ukupnaCena + ", " + kupac.getKupacID() + ", " + prodavac.getProdavacID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumDostave = '" + new java.sql.Date(datumDostave.getTime()) + "', "
                + "ukupnaCena = " + ukupnaCena + " ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getPorudzbinaID() {
        return porudzbinaID;
    }

    public void setPorudzbinaID(Long porudzbinaID) {
        this.porudzbinaID = porudzbinaID;
    }

    public Date getDatumVremePorucivanja() {
        return datumVremePorucivanja;
    }

    public void setDatumVremePorucivanja(Date datumVremePorucivanja) {
        this.datumVremePorucivanja = datumVremePorucivanja;
    }

    public Date getDatumDostave() {
        return datumDostave;
    }

    public void setDatumDostave(Date datumDostave) {
        this.datumDostave = datumDostave;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public ArrayList<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }

}
