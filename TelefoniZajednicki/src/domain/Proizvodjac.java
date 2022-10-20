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
public class Proizvodjac extends AbstractDomainObject {
    
    private Long proizvodjacID;
    private String nazivProizvodjaca;

    @Override
    public String toString() {
        return nazivProizvodjaca;
    }

    public Proizvodjac(Long proizvodjacID, String nazivProizvodjaca) {
        this.proizvodjacID = proizvodjacID;
        this.nazivProizvodjaca = nazivProizvodjaca;
    }

    public Proizvodjac() {
    }
    
    @Override
    public String nazivTabele() {
        return " proizvodjac ";
    }

    @Override
    public String alijas() {
        return " pr ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Proizvodjac pr = new Proizvodjac(rs.getLong("ProizvodjacID"),
                    rs.getString("NazivProizvodjaca"));

            lista.add(pr);
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
        return " ProizvodjacID = " + proizvodjacID;
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
        return "";
    }

    public Long getProizvodjacID() {
        return proizvodjacID;
    }

    public void setProizvodjacID(Long proizvodjacID) {
        this.proizvodjacID = proizvodjacID;
    }

    public String getNazivProizvodjaca() {
        return nazivProizvodjaca;
    }

    public void setNazivProizvodjaca(String nazivProizvodjaca) {
        this.nazivProizvodjaca = nazivProizvodjaca;
    }
    
}
