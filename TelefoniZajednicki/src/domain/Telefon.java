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
public class Telefon extends AbstractDomainObject {

    private Long telefonID;
    private String nazivTelefona;
    private double cena;
    private String specifikacije;
    private Proizvodjac proizvodjac;

    @Override
    public String toString() {
        return nazivTelefona + " (Cena: " + cena + "€)";
    }

    public Telefon(Long telefonID, String nazivTelefona, double cena, String specifikacije, Proizvodjac proizvodjac) {
        this.telefonID = telefonID;
        this.nazivTelefona = nazivTelefona;
        this.cena = cena;
        this.specifikacije = specifikacije;
        this.proizvodjac = proizvodjac;
    }

    public Telefon() {
    }

    @Override
    public String nazivTabele() {
        return " telefon ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN PROIZVODJAC PR ON (PR.PROIZVODJACID = T.PROIZVODJACID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Proizvodjac pr = new Proizvodjac(rs.getLong("ProizvodjacID"),
                    rs.getString("NazivProizvodjaca"));

            Telefon t = new Telefon(rs.getLong("telefonID"), rs.getString("nazivTelefona"),
                    rs.getDouble("cena"), rs.getString("specifikacije"), pr);

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " telefonID = " + telefonID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        if (proizvodjac == null) {
            return "";
        }
        return " WHERE PR.PROIZVODJACID = " + proizvodjac.getProizvodjacID();
    }

    public Long getTelefonID() {
        return telefonID;
    }

    public void setTelefonID(Long telefonID) {
        this.telefonID = telefonID;
    }

    public String getNazivTelefona() {
        return nazivTelefona;
    }

    public void setNazivTelefona(String nazivTelefona) {
        this.nazivTelefona = nazivTelefona;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getSpecifikacije() {
        return specifikacije;
    }

    public void setSpecifikacije(String specifikacije) {
        this.specifikacije = specifikacije;
    }

    public Proizvodjac getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(Proizvodjac proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

}
