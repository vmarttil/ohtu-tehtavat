package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Viitegeneraattori;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class KauppaTest {

	Pankki pankki;
	Kauppa k;
	Varasto varasto;
	Viitegeneraattori viite;
	
	@Before
	public void SetUp() {
		// luodaan ensin mock-oliot
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        k = new Kauppa(varasto, pankki, viite);  
	}
	
	
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);   
    }
    
    @Test
    public void kahdenEriOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {        
        // määritellään että tuote numero 2 on leipä jonka hinta on 3 ja saldo 20
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 3));
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli leipää
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",8);   
    }
    
    @Test
    public void kahdenSamanOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {          

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan lisää tuotetta numero 1 eli maitoa
        k.tilimaksu("jussi", "54321");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto("jussi", 42, "54321", "33333-44455",10);   
    }
    
    @Test
    public void loppuneenTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että tuote numero 2 on leipä jonka hinta on 3 ja saldo 0
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 3));
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // yritetään ostaa tuotetta 2 eli leipää
        k.tilimaksu("matti", "11223");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto("matti", 42, "11223", "33333-44455",5);   
    }
    
    @Test
    public void uudenAsioinninAloituksenJalkeenPankinMetodiaTilisiirtoKutsutaan() {        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan lisää tuotetta numero 1 eli maitoa
        // aloitetaan uusi asiointi
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan taas tuotetta numero 1 eli maitoa
        k.tilimaksu("jussi", "12555");
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto("jussi", 42, "12555", "33333-44455",5);   
    }
    
    @Test
    public void pyydetaanUusiViitenumeroJokaiselleMaksutapahtumalle() {
        // määritellään viitegeneraattori uusiksi niin, että se palauttaa ensimmäisellä kutsukerralla 11, 
    	// toisella 22 ja kolmannella 33
        when(viite.uusi()).thenReturn(11).thenReturn(22).thenReturn(33);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("joonas", "12321");

        // varmistetaan, että käytössä on ensimmäisenä pyydetty viite
        verify(pankki).tilisiirto("joonas", 11, "12321", "33333-44455",5);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("joonas", "12321");

        // varmistetaan, että nyt käytössä on toisena pyydetty viite
        verify(pankki).tilisiirto("joonas", 22, "12321", "33333-44455",5);   
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("joonas", "12321");

        // varmistetaan, että nyt käytössä on kolmantena pyydetty viite
        verify(pankki).tilisiirto("joonas", 33, "12321", "33333-44455",5);           
    }
    
    @Test
    public void tuotteidenLisaamisenJaPoistamisenJalkeenPankinMetodiaTilisiirtoKutsutaan() {        
        // tehdään ostoksia
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan lisää tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan vielä lisää tuotetta numero 1 eli maitoa
        k.poistaKorista(1);   // poistetaan korista yksi tuote
        k.tilimaksu("jussi", "12555");
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto("jussi", 42, "12555", "33333-44455",10);   
    }
    
    
    
}