/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author darre
 */
public class AppConfig {
    static public int jamBuka = 10;
    static public int jamTutup = 22;
    static public int persenDP = 20;
    static public int maksDp = 5;

    public static int getPersenDP() {
        return persenDP;
    }

    public static void setPersenDP(int persenDP) {
        AppConfig.persenDP = persenDP;
    }

    public static int getJamBuka() {
        return jamBuka;
    }

    public static void setJamBuka(int jamBuka) {
        AppConfig.jamBuka = jamBuka;
    }

    public static int getJamTutup() {
        return jamTutup;
    }

    public static void setJamTutup(int jamTutup) {
        AppConfig.jamTutup = jamTutup;
    }
    
    
    
}
