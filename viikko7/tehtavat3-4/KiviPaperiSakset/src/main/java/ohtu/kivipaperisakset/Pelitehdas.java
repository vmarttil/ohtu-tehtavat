/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

/**
 *
 * @author vmarttil
 */
public class Pelitehdas {
    
    public static KPS luoTekoalyKPS(IO io) {
        return new KPSTekoaly(new Ihminen(1, io), new Tekoaly(), io);
    }
    
    public static KPS luoParempiTekoalyKPS(IO io) {
        return new KPSParempiTekoaly(new Ihminen(1, io), new TekoalyParannettu(20), io);
    }
    
    public static KPS luoPelaajaKPS(IO io) {
        return new KPSPelaajaVsPelaaja(new Ihminen(1, io), new Ihminen(2, io), io);
    }
    
}
