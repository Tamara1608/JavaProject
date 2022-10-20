/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.proizvodjac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Prodavac;
import domain.Proizvodjac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllProizvodjac extends AbstractSO {

    private ArrayList<Proizvodjac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Proizvodjac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Proizvodjac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> proizvodjaci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Proizvodjac>) (ArrayList<?>) proizvodjaci;
    }

    public ArrayList<Proizvodjac> getLista() {
        return lista;
    }

}
