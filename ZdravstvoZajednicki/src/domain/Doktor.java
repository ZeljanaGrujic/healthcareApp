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
public class Doktor extends AbstractDomainObject implements Serializable {

    private Long doktorID;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private Organizacija organizacija;

    public Doktor() {
    }

    public Doktor(Long doktorID, String ime, String prezime, String username, String password, Organizacija organizacija) {
        this.doktorID = doktorID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.organizacija = organizacija;
    }

    public Long getDoktorID() {
        return doktorID;
    }

    public void setDoktorID(Long doktorID) {
        this.doktorID = doktorID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " doktor ";
    }

    @Override
    public String alijas() {
        return " d ";
    }

    @Override
    public String join() {
        return " JOIN zdravstvenaOrganizacija zo ON (zo.organizacijaid = d.organizacijaid) ";
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

            lista.add(d);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password, OrganizacijaID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " DoktorID = " + doktorID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password + "'" + ", " + organizacija.getOrganizacijaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Organizacija getOrganizacija() {
        return organizacija;
    }

    public void setOrganizacija(Organizacija organizacija) {
        this.organizacija = organizacija;
    }

}
