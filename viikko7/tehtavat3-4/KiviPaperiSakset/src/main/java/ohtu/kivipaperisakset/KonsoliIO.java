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
public class KonsoliIO implements IO {
    
    private static final Scanner lukija = new Scanner(System.in);

    public KonsoliIO() {
    }

    @Override
    public String kysyPelimuoto() {
        System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");
            String vastaus = this.lukija.nextLine();
            return vastaus;
    }
    
    
    @Override
    public String lueSiirto(String kysymys) {
        System.out.print(kysymys);
        String siirto = this.lukija.nextLine();
        return siirto;
    }

    @Override
    public void tulosta(String m) {
        System.out.println(m);
    }
}
