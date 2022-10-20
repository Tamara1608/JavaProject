/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.porudzbina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Porudzbina;
import domain.StavkaPorudzbine;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdatePorudzbina extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Porudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Porudzbina!");
        }

        Porudzbina p = (Porudzbina) ado;

        if (!p.getDatumDostave().after(new Date())) {
            throw new Exception("Datum dostave mora biti posle danasnjeg datuma!");
        }

        if (p.getStavkePorudzbine().isEmpty()) {
            throw new Exception("Porudzbina mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // updatujemo porudzbinu
        DBBroker.getInstance().update(ado);
        
        Porudzbina p = (Porudzbina) ado;
        // brisemo stare stavke
        // sledeca linija koda izvrsava naredbu
        // DELETE FROM STAVKAPORUDZBINE WHERE PORUDZBINAID = nasID
        // cime se brisu SVE stavke nase porudzbine ODJEDNOM !!!
        DBBroker.getInstance().delete(p.getStavkePorudzbine().get(0));
        
        
        // dodajemo nove
        for (StavkaPorudzbine stavkaPorudzbine : p.getStavkePorudzbine()) {
            DBBroker.getInstance().insert(stavkaPorudzbine);
        }
        
        
    }

}
