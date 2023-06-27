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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddTermin extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }
        
        Termin t = (Termin) ado;
        
        // termin ne sme imati praznu listu stavki
        if(t.getStavkeTermina().isEmpty()){
            throw new Exception("Morate uneti barem jednu stavku za ovaj termin!");
        }
        
        ArrayList<Termin> termini = (ArrayList<Termin>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Termin termin : termini) {
            if (t.getDoktor().getDoktorID().equals(termin.getDoktor().getDoktorID())) {
                // za doktora
                if(t.getDatumVreme().equals(termin.getDatumVreme())){
                    throw new Exception("Doktor " + t.getDoktor() + " vec ima termin zakazan u to vreme!");
                }
                
            }
            // za pacijenta
            if(t.getPacijent().getPacijentID().equals(termin.getPacijent().getPacijentID())){
                if(t.getDatumVreme().equals(termin.getDatumVreme())){
                    throw new Exception("Pacijent " + t.getPacijent() + " vec ima termin zakazan u to vreme!");
                }
            }
            
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long terminID = tableKeys.getLong(1);
        
        Termin t = (Termin) ado;
        t.setTerminID(terminID);
        
        for (StavkaTermina stavkaTermina : t.getStavkeTermina()) {
            stavkaTermina.setTermin(t);
            DBBroker.getInstance().insert(stavkaTermina);
        }
        
    }

}
