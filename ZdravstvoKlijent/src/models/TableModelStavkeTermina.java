/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.StavkaTermina;
import domain.Termin;
import domain.Usluga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelStavkeTermina extends AbstractTableModel {

    private ArrayList<StavkaTermina> lista;
    private String[] kolone = {"Rb", "Usluga", "Cena"};
    private int rb = 0;

    public TableModelStavkeTermina() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeTermina(Termin t) {
        try {
            lista = ClientController.getInstance().getAllStavkaTermina(t);
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkeTermina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaTermina st = lista.get(row);

        switch (column) {
            case 0:
                return st.getRbStavke();
            case 1:
                return st.getUsluga().getNazivUsluge();
            case 2:
                return st.getCenaStavke();

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaTermina st) {
        
        rb = lista.size();
        
        st.setRbStavke(++rb);
        lista.add(st);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);
        
        rb = 0;
        for (StavkaTermina stavkaTermina : lista) {
            stavkaTermina.setRbStavke(++rb);
        }
        
        fireTableDataChanged();
        
    }

    public StavkaTermina getSelectedStavka(int row) {
        return lista.get(row);
    }

    public boolean postojiUsluga(Usluga u) {
        for (StavkaTermina stavkaTermina : lista) {
            if(stavkaTermina.getUsluga().getUslugaID().equals(u.getUslugaID())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<StavkaTermina> getLista() {
        return lista;
    }

    public void isprazniTabelu() {
        lista.removeAll(lista);
        fireTableDataChanged();
    }

}
