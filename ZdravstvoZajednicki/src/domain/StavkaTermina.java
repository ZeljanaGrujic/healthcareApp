/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class StavkaTermina extends AbstractDomainObject implements Serializable {

    private Termin termin;
    private int rbStavke;
    private double cenaStavke;
    private Usluga usluga;

    public StavkaTermina(Termin termin, int rbStavke, double cenaStavke, Usluga usluga) {
        this.termin = termin;
        this.rbStavke = rbStavke;
        this.cenaStavke = cenaStavke;
        this.usluga = usluga;
    }

    public StavkaTermina() {
    }

    @Override
    public String nazivTabele() {
        return " stavkaTermina ";
    }

    @Override
    public String alijas() {
        return " st ";
    }

    @Override
    public String join() {
        return " JOIN usluga u ON (u.uslugaid = st.uslugaid) "
                + "JOIN termin t ON (t.terminid = st.terminid) "
                + "JOIN pacijent p ON (p.pacijentid = t.pacijentid) "
                + "JOIN doktor d ON (d.doktorid = t.doktorid) "
                + "JOIN zdravstvenaOrganizacija zo ON (zo.organizacijaid = d.organizacijaid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Organizacija o = new Organizacija(rs.getLong("OrganizacijaID"),
                    rs.getString("NazivOrganizacije"));

            Doktor d = new Doktor(rs.getLong("DoktorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"), o);
            
            Usluga u = new Usluga(rs.getLong("UslugaID"),
                    rs.getString("NazivUsluge"), rs.getString("Opis"), rs.getDouble("Cena"));
            
            Pacijent p = new Pacijent(rs.getLong("PacijentID"),
                    rs.getString("ImePacijenta"), rs.getString("PrezimePacijenta"),
                    rs.getString("Email"), rs.getString("Telefon"), rs.getString("Napomena"));
            
            Termin t = new Termin(rs.getLong("TerminID"), rs.getTimestamp("DatumVreme"), 
                    rs.getDouble("CenaTermina"), p, d, null);
            
            StavkaTermina st = new StavkaTermina(t, rs.getInt("RbStavke"), 
                    rs.getDouble("cenaStavke"), u);

            lista.add(st);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (TerminID, RbStavke, CenaStavke, UslugaID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " TerminID = " + termin.getTerminID();
    }

    @Override
    public String vrednostiZaInsert() {
        return termin.getTerminID() + ", " + rbStavke + ", "
                + cenaStavke + ", " + usluga.getUslugaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return " WHERE T.TERMINID = " + termin.getTerminID();
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }
}
