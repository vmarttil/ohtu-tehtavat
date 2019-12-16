package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final IO io = new KonsoliIO();

    public static void main(String[] args) {

        while (true) {
            Pelitehdas pelitehdas = new Pelitehdas();
            String pelimuoto = io.kysyPelimuoto();
            
            io.tulosta("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            if (pelimuoto.endsWith("a")) {
                KPS peli = pelitehdas.luoPelaajaKPS(io);
                peli.pelaa();
            } else if (pelimuoto.endsWith("b")) {
                KPS peli = pelitehdas.luoTekoalyKPS(io);
                peli.pelaa();
            } else if (pelimuoto.endsWith("c")) {
                KPS peli = pelitehdas.luoParempiTekoalyKPS(io);
                peli.pelaa();
            } else {
                break;
            }

        }

    }
}
