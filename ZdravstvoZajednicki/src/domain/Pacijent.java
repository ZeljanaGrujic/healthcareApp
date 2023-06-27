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
public class Pacijent extends AbstractDomainObject implements Serializable {
    
    private Long pacijentID;
    private String imePacijenta;
    private String prezimePacijenta;
    private String email;
    private String telefon;
    private String napomena;

    @Override
    public String toString() {
        return imePacijenta + " " + prezimePacijenta;
    }

    public Pacijent() {
    }

    public Pacijent(Long pacijentID, String imePacijenta, String prezimePacijenta, String email, String telefon, String napomena) {
        this.pacijentID = pacijentID;
        this.imePacijenta = imePacijenta;
        this.prezimePacijenta = prezimePacijenta;
        this.email = email;
        this.telefon = telefon;
        this.napomena = napomena;
    }
    
    @Override
    public String nazivTabele() {
        return " pacijent ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Pacijent p = new Pacijent(rs.getLong("PacijentID"),
                    rs.getString("ImePacijenta"), rs.getString("PrezimePacijenta"),
                    rs.getString("Email"), rs.getString("Telefon"), rs.getString("Napomena"));

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ImePacijenta, PrezimePacijenta, Email, Telefon, Napomena) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " PacijentID = " + pacijentID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + imePacijenta + "', '" + prezimePacijenta + "', "
                + "'" + email + "', '" + telefon + "', '" + napomena + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "email = '" + email + "', telefon = '" + telefon + "', "
                + "Napomena = '" + napomena + "' ";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getPacijentID() {
        return pacijentID;
    }

    public void setPacijentID(Long pacijentID) {
        this.pacijentID = pacijentID;
    }

    public String getImePacijenta() {
        return imePacijenta;
    }

    public void setImePacijenta(String imePacijenta) {
        this.imePacijenta = imePacijenta;
    }

    public String getPrezimePacijenta() {
        return prezimePacijenta;
    }

    public void setPrezimePacijenta(String prezimePacijenta) {
        this.prezimePacijenta = prezimePacijenta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
    
}
