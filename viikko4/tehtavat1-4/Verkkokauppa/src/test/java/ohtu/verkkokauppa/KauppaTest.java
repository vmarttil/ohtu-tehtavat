package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

	Pankki pankki;
	Kauppa k;
	Varasto varasto;
	
	@Before
	public void SetUp() {
		// luodaan ensin mock-oliot
        pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
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

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
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

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",8);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void kahdenSamanOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {          

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan lisää tuotetta numero 1 eli maitoa
        k.tilimaksu("jussi", "54321");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("jussi", 42, "54321", "33333-44455",10);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
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

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("matti", 42, "11223", "33333-44455",5);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
}