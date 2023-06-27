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
public class Organizacija extends AbstractDomainObject implements Serializable {

    private Long organizacijaID;
    private String nazivOrganizacije;

    @Override
    public String toString() {
        return nazivOrganizacije;
    }

    public Organizacija(Long organizacijaID, String nazivOrganizacije) {
        this.organizacijaID = organizacijaID;
        this.nazivOrganizacije = nazivOrganizacije;
    }

    public Organizacija() {
    }

    @Override
    public String nazivTabele() {
        return " zdravstvenaOrganizacija ";
    }

    @Override
    public String alijas() {
        return " zo ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Organizacija o = new Organizacija(rs.getLong("OrganizacijaID"),
                    rs.getString("NazivOrganizacije"));

            lista.add(o);
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
        return " OrganizacijaID = " + organizacijaID;
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
    public String getByID() {
        return "";
    }

    public Long getOrganizacijaID() {
        return organizacijaID;
    }

    public void setOrganizacijaID(Long organizacijaID) {
        this.organizacijaID = organizacijaID;
    }

    public String getNazivOrganizacije() {
        return nazivOrganizacije;
    }

    public void setNazivOrganizacije(String nazivOrganizacije) {
        this.nazivOrganizacije = nazivOrganizacije;
    }
}
