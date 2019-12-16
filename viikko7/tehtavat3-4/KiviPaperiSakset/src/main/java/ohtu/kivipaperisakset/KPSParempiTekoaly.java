package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPS {

    public KPSParempiTekoaly(Pelaaja pelaaja1, Pelaaja pelaaja2, IO io) {
        super(pelaaja1, pelaaja2, io); 
    }
    
    @Override
    String kysyTokanSiirto() {
        String tokanSiirto = pelaaja2.annaSiirto();
        io.tulosta("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
    }

}
