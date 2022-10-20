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
public class Kupac extends AbstractDomainObject {

    private Long kupacID;
    private String PIB;
    private String nazivKupca;
    private String email;
    private String telefon;

    @Override
    public String toString() {
        return nazivKupca;
    }

    public Kupac(Long kupacID, String PIB, String nazivKupca, String email, String telefon) {
        this.kupacID = kupacID;
        this.PIB = PIB;
        this.nazivKupca = nazivKupca;
        this.email = email;
        this.telefon = telefon;
    }

    public Kupac() {
    }

    @Override
    public String nazivTabele() {
        return " kupac ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("PIB"), rs.getString("NazivKupca"),
                    rs.getString("Email"), rs.getString("Telefon"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (PIB, NazivKupca, Email, Telefon) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " KupacID = " + kupacID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + PIB + "', '" + nazivKupca + "', "
                + "'" + email + "', '" + telefon + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', telefon = '" + telefon + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getKupacID() {
        return kupacID;
    }

    public void setKupacID(Long kupacID) {
        this.kupacID = kupacID;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public String getNazivKupca() {
        return nazivKupca;
    }

    public void setNazivKupca(String nazivKupca) {
        this.nazivKupca = nazivKupca;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

}
