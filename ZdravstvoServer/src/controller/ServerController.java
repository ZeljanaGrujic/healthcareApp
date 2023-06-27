/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Doktor;
import domain.Organizacija;
import domain.Pacijent;
import domain.StavkaTermina;
import domain.Termin;
import domain.Usluga;
import java.util.ArrayList;
import so.AbstractSO;
import so.doktor.SOAddDoktor;
import so.doktor.SOGetAllDoktor;
import so.pacijent.SOAddPacijent;
import so.pacijent.SODeletePacijent;
import so.pacijent.SOUpdatePacijent;
import so.pacijent.SOGetAllPacijent;
import so.stavkaTermina.SOAddStavkaTermina;
import so.stavkaTermina.SODeleteStavkaTermina;
import so.stavkaTermina.SOGetAllStavkaTermina;
import so.termin.SOAddTermin;
import so.termin.SODeleteTermin;
import so.termin.SOGetAllTermin;
import so.termin.SOUpdateTermin;
import so.usluga.SOGetAllUsluga;
import so.zdravstvenaOrganizacija.SOGetAllZdravstvenaOrganizacija;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void addDoktor(Doktor doktor) throws Exception {

        AbstractSO aso = new SOAddDoktor();
        aso.templateExecute(doktor);
    }

    public void addPacijent(Pacijent pacijent) throws Exception {
        AbstractSO aso = new SOAddPacijent();
        aso.templateExecute(pacijent);
    }

    public void addTermin(Termin termin) throws Exception {
        AbstractSO aso = new SOAddTermin();
        aso.templateExecute(termin);
    }

    public void addStavkaTermina(StavkaTermina stavkaTermina) throws Exception {
        AbstractSO aso = new SOAddStavkaTermina();
        aso.templateExecute(stavkaTermina);
    }

    public void deletePacijent(Pacijent pacijent) throws Exception {
        AbstractSO aso = new SODeletePacijent();
        aso.templateExecute(pacijent);
    }

    public void deleteTermin(Termin termin) throws Exception {
        AbstractSO aso = new SODeleteTermin();
        aso.templateExecute(termin);
    }

    public void deleteStavkaTermina(StavkaTermina stavkaTermina) throws Exception {
        AbstractSO aso = new SODeleteStavkaTermina();
        aso.templateExecute(stavkaTermina);
    }

    public void updatePacijent(Pacijent pacijent) throws Exception {
        AbstractSO aso = new SOUpdatePacijent();
        aso.templateExecute(pacijent);
    }

    public void updateTermin(Termin termin) throws Exception {
        AbstractSO aso = new SOUpdateTermin();
        aso.templateExecute(termin);
    }

    public ArrayList<Pacijent> getAllPacijent() throws Exception {
        SOGetAllPacijent so = new SOGetAllPacijent();
        so.templateExecute(new Pacijent());
        return so.getLista();
    }

    public ArrayList<Doktor> getAllDoktor() throws Exception {
        SOGetAllDoktor so = new SOGetAllDoktor();
        so.templateExecute(new Doktor());
        return so.getLista();
    }

    public ArrayList<Termin> getAllTermin(Pacijent p) throws Exception {
        SOGetAllTermin so = new SOGetAllTermin();

        Termin t = new Termin();
        t.setPacijent(p);

        so.templateExecute(t);
        return so.getLista();
    }

    public ArrayList<StavkaTermina> getAllStavkaTermina(Termin t) throws Exception {
        SOGetAllStavkaTermina so = new SOGetAllStavkaTermina();

        StavkaTermina st = new StavkaTermina();
        st.setTermin(t);

        so.templateExecute(st);
        return so.getLista();
    }

    public ArrayList<Organizacija> getAllOrganizacija() throws Exception {
        SOGetAllZdravstvenaOrganizacija so = new SOGetAllZdravstvenaOrganizacija();
        so.templateExecute(new Organizacija());
        return so.getLista();
    }

    public ArrayList<Usluga> getAllUsluga() throws Exception {
        SOGetAllUsluga so = new SOGetAllUsluga();
        so.templateExecute(new Usluga());
        return so.getLista();
    }

}
