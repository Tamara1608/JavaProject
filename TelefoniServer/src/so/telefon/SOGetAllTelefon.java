/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.telefon;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Telefon;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllTelefon extends AbstractSO {

    private ArrayList<Telefon> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Telefon)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Telefon!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> telefoni = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Telefon>) (ArrayList<?>) telefoni;
    }

    public ArrayList<Telefon> getLista() {
        return lista;
    }

}
