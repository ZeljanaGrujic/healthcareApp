/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaTermina;
import domain.Termin;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateTermin extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }

        Termin t = (Termin) ado;

        // termin ne sme imati praznu listu stavki
        if (t.getStavkeTermina().isEmpty()) {
            throw new Exception("Morate uneti barem jednu stavku za ovaj termin!");
        }

        ArrayList<Termin> termini = (ArrayList<Termin>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Termin termin : termini) {

            if (!t.getTerminID().equals(termin.getTerminID())) {
                if (t.getDoktor().getDoktorID().equals(termin.getDoktor().getDoktorID())) {
                    // za doktora
                    if (t.getDatumVreme().equals(termin.getDatumVreme())) {
                        throw new Exception("Doktor " + t.getDoktor() + " vec ima termin zakazan u to vreme!");
                    }

                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().update(ado);

        Termin t = (Termin) ado;

        
        // DELETE FROM STAVKATERMINA WHERE TERMINID = npr 5
        DBBroker.getInstance().delete(t.getStavkeTermina().get(0));

        
        for (StavkaTermina stavkaTermina : t.getStavkeTermina()) {
            DBBroker.getInstance().insert(stavkaTermina);
        }

    }

}
