package ohtu.verkkokauppa;

import java.util.ArrayList;

public class Ostoskori {

    ArrayList<TuoteInt> tuotteet;

    public Ostoskori() {
        tuotteet = new ArrayList<TuoteInt>();
    }

    public void lisaa(TuoteInt t) {
        tuotteet.add(t);
    }

    public void poista(TuoteInt t) {
        tuotteet.remove(t);
    }

    public int hinta() {
        int hinta = 0;

        for (TuoteInt tuote : tuotteet) {
            hinta += tuote.getHinta();
        }

        return hinta;
    }
}
