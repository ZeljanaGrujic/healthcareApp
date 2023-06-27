/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.doktor;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Doktor;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author zelja
 */
public class SOAddDoktor extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        
        if (!(ado instanceof Doktor)) {
            throw new Exception(" Prosledjeni objekat nije instanca klase Doktor");
        }
        Doktor doc = (Doktor) ado;

        ArrayList<Doktor> listaDoktora = (ArrayList<Doktor>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Doktor doktor : listaDoktora) {
            if (doktor.getIme().equals(doc.getIme()) && doktor.getPrezime().equals(doc.getPrezime())
                    && doktor.getUsername().equals(doc.getUsername())) {
                throw new Exception("Doktor vec postoji u bazi");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        DBBroker.getInstance().insert(ado);
    }

}
