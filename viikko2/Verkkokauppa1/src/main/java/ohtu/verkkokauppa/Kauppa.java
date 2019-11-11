package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    private VarastoInt varasto;
    private PankkiInt pankki;
    private Ostoskori ostoskori;
    private ViitegeneraattoriInt viitegeneraattori;
    private String kaupanTili;

    @Autowired
    public Kauppa(VarastoInt varasto, PankkiInt pankki, ViitegeneraattoriInt viitegeneraattori) {
        this.varasto = varasto;
        this.pankki = pankki;
        this.viitegeneraattori = viitegeneraattori;
        this.kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        this.ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        TuoteInt t = this.varasto.haeTuote(id); 
        this.varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (this.varasto.saldo(id)>0) {
            TuoteInt t = this.varasto.haeTuote(id);             
            this.ostoskori.lisaa(t);
            this.varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = this.viitegeneraattori.uusi();
        int summa = this.ostoskori.hinta();
        
        return this.pankki.tilisiirto(nimi, viite, tiliNumero, this.kaupanTili, summa);
    }

}
