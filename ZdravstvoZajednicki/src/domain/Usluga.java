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
public class Usluga extends AbstractDomainObject implements Serializable {
    
    private Long uslugaID;
    private String nazivUsluge;
    private String opis;
    private double cena;
    
    @Override
    public String toString() {
        return nazivUsluge + " (Inicijalna cena: " + cena + "din )";
    }

    public Usluga(Long uslugaID, String nazivUsluge, String opis, double cena) {
        this.uslugaID = uslugaID;
        this.nazivUsluge = nazivUsluge;
        this.opis = opis;
        this.cena = cena;
    }

    public Usluga() {
    }
    
    @Override
    public String nazivTabele() {
        return " usluga ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Usluga u = new Usluga(rs.getLong("UslugaID"),
                    rs.getString("NazivUsluge"), rs.getString("Opis"), rs.getDouble("Cena"));

            lista.add(u);
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
        return " UslugaID = " + uslugaID;
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

    public Long getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(Long uslugaID) {
        this.uslugaID = uslugaID;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
