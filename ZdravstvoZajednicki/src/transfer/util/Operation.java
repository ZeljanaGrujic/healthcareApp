/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int GET_ALL_DOKTOR = 1;

    public static final int ADD_PACIJENT = 2;
    public static final int DELETE_PACIJENT = 3;
    public static final int UPDATE_PACIJENT = 4;
    public static final int GET_ALL_PACIJENT = 5;

    public static final int GET_ALL_USLUGA = 6;

    public static final int GET_ALL_ZDRAVSTVENA_ORGANIZACIJA = 7;

    public static final int ADD_TERMIN = 8;
    public static final int DELETE_TERMIN = 9;
    public static final int UPDATE_TERMIN = 10;
    public static final int GET_ALL_TERMIN = 11;

    public static final int ADD_STAVKA_TERMINA = 12;
    public static final int DELETE_STAVKA_TERMINA = 13;
    public static final int GET_ALL_STAVKA_TERMINA = 14;
    
    
    public static final int ADD_DOKTOR = 15;

}
