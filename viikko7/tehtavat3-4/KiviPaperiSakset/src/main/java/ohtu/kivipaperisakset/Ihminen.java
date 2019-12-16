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
public class Ihminen implements Pelaaja {
    int numero;
    IO io;
    
    public Ihminen(int numero, IO io) {
        this.numero = numero;
        this.io = io;
    }
    
    @Override
    public String annaSiirto() {
        if (numero == 1) {
            return io.lueSiirto("Ensimmäisen pelaajan siirto: ");
        } else {
            return io.lueSiirto("Toisen pelaajan siirto: ");
        }
    }

    @Override
    public void asetaSiirto(String ekanSiirto) {
        // ei tehdä mitään 
    }
    
    
}
