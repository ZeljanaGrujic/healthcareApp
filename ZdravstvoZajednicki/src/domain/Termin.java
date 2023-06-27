/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Termin extends AbstractDomainObject implements Serializable {

    private Long terminID;
    private Date datumVreme;
    private double cenaTermina;
    private Pacijent pacijent;
    private Doktor doktor;
    private ArrayList<StavkaTermina> stavkeTermina;

    public Termin(Long terminID, Date datumVreme, double cenaTermina, Pacijent pacijent, Doktor doktor, ArrayList<StavkaTermina> stavkeTermina) {
        this.terminID = terminID;
        this.datumVreme = datumVreme;
        this.cenaTermina = cenaTermina;
        this.pacijent = pacijent;
        this.doktor = doktor;
        this.stavkeTermina = stavkeTermina;
    }

    public Termin() {
    }

    @Override
    public String nazivTabele() {
        return " termin ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN pacijent p ON (p.pacijentid = t.pacijentid) "
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
            
            Pacijent p = new Pacijent(rs.getLong("PacijentID"),
                    rs.getString("ImePacijenta"), rs.getString("PrezimePacijenta"),
                    rs.getString("Email"), rs.getString("Telefon"), rs.getString("Napomena"));
            
            Termin t = new Termin(rs.getLong("TerminID"), rs.getTimestamp("DatumVreme"), 
                    rs.getDouble("CenaTermina"), p, d, null);

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DatumVreme, CenaTermina, PacijentID, DoktorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " TerminID = " + terminID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', "
                + cenaTermina + ", "
                + pacijent.getPacijentID() + ", " + doktor.getDoktorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " DatumVreme = '" + new Timestamp(datumVreme.getTime()) 
                + "', CenaTermina = " + cenaTermina + ", "
                + "PacijentID = " + pacijent.getPacijentID();
    }

    @Override
    public String getByID() {
        if(pacijent != null){
            return " WHERE P.PACIJENTID = " + pacijent.getPacijentID();
        }
        return "";
    }

    public Long getTerminID() {
        return terminID;
    }

    public void setTerminID(Long terminID) {
        this.terminID = terminID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getCenaTermina() {
        return cenaTermina;
    }

    public void setCenaTermina(double cenaTermina) {
        this.cenaTermina = cenaTermina;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
    }

    public ArrayList<StavkaTermina> getStavkeTermina() {
        return stavkeTermina;
    }

    public void setStavkeTermina(ArrayList<StavkaTermina> stavkeTermina) {
        this.stavkeTermina = stavkeTermina;
    }
}
