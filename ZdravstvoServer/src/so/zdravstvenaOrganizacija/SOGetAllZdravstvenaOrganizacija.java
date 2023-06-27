/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zdravstvenaOrganizacija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Organizacija;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllZdravstvenaOrganizacija extends AbstractSO {

    private ArrayList<Organizacija> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Organizacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Organizacija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> organizacije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Organizacija>) (ArrayList<?>) organizacije;
    }

    public ArrayList<Organizacija> getLista() {
        return lista;
    }

}
