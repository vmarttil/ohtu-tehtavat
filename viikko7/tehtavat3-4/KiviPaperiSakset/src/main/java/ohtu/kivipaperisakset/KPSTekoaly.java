package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPS {
        
    public KPSTekoaly(Pelaaja pelaaja1, Pelaaja pelaaja2, IO io) {
        super(pelaaja1, pelaaja2, io); 
    }
    
    @Override
    String kysyTokanSiirto() {
        String tokanSiirto = pelaaja2.annaSiirto();
        io.tulosta("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
    }
}