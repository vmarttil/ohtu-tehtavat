package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pankki implements PankkiInt {

    private KirjanpitoInt kirjanpito;

    @Autowired
    public Pankki(KirjanpitoInt kirjanpito) {
    	this.kirjanpito = kirjanpito;
    }

    @Override
	public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
    	this.kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
