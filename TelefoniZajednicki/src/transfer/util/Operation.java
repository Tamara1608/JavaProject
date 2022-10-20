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

    public static final int GET_ALL_PRODAVAC = 1;

    public static final int ADD_KUPAC = 2;
    public static final int DELETE_KUPAC = 3;
    public static final int UPDATE_KUPAC = 4;
    public static final int GET_ALL_KUPAC = 5;

    public static final int ADD_PORUDZBINA = 6;
    public static final int DELETE_PORUDZBINA = 7;
    public static final int UPDATE_PORUDZBINA = 8;
    public static final int GET_ALL_PORUDZBINA = 9;

    public static final int GET_ALL_STAVKA_PORUDZBINE = 10;

    public static final int GET_ALL_PROIZVODJAC = 11;
    
//    public static final int ADD_TELEFON = 12;
//    public static final int DELETE_TELEFON = 13;
//    public static final int UPDATE_TELEFON = 14;
    public static final int GET_ALL_TELEFON = 15;

}
