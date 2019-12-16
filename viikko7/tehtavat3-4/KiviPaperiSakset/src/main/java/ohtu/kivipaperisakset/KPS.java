/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author vmarttil
 */
public abstract class KPS {
    Pelaaja pelaaja1;
    Pelaaja pelaaja2;
    String ekanSiirto;
    String tokanSiirto;
    IO io;

    public KPS(Pelaaja pelaaja1, Pelaaja pelaaja2, IO io) {
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
        this.io = io;
    }
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
       
        this.ekanSiirto = pelaaja1.annaSiirto();
        this.tokanSiirto = kysyTokanSiirto();
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            this.io.tulosta(tuomari.toString());
            this.io.tulosta("");
            this.ekanSiirto = pelaaja1.annaSiirto();
            this.tokanSiirto = kysyTokanSiirto();
        }
        this.io.tulosta("");
        this.io.tulosta("Kiitos!");
        this.io.tulosta(tuomari.toString());
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
    abstract String kysyTokanSiirto();
}
