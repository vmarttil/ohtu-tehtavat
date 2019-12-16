package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPS {
    
    public KPSPelaajaVsPelaaja(Pelaaja pelaaja1, Pelaaja pelaaja2, IO io) {
        super(pelaaja1, pelaaja2, io); 
    }

@Override
    String kysyTokanSiirto() {
        return pelaaja2.annaSiirto();
    }
}