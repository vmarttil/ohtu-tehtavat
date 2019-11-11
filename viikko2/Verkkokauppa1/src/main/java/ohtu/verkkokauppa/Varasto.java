package ohtu.verkkokauppa;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Varasto implements VarastoInt {
    
    private KirjanpitoInt kirjanpito;
    private HashMap<TuoteInt, Integer> saldot;  
    
    @Autowired
    public Varasto(KirjanpitoInt kirjanpito) {
        this.kirjanpito = kirjanpito;
        this.saldot = new HashMap<TuoteInt, Integer>();
        alustaTuotteet();
    }
            
    @Override
	public TuoteInt haeTuote(int id){
        for (TuoteInt t : this.saldot.keySet()) {
            if ( t.getId()==id) return t;
        }
        return null;
    }

    @Override
	public int saldo(int id){
        return this.saldot.get(haeTuote(id));
    }
    
    @Override
	public void otaVarastosta(TuoteInt t){        
        this.saldot.put(t,  saldo(t.getId())-1 );
        this.kirjanpito.lisaaTapahtuma("otettiin varastosta "+t);
    }
    
    @Override
	public void palautaVarastoon(TuoteInt t){
        this.saldot.put(t,  saldo(t.getId())+1 );
        this.kirjanpito.lisaaTapahtuma("palautettiin varastoon "+t);
    }    
    
    private void alustaTuotteet() {
        this.saldot.put(new Tuote(1, "Koff Portteri", 3), 100);
        this.saldot.put(new Tuote(2, "Fink Bräu I", 1), 25);
        this.saldot.put(new Tuote(3, "Sierra Nevada Pale Ale", 5), 30);
        this.saldot.put(new Tuote(4, "Mikkeller not just another Wit", 7), 40);
        this.saldot.put(new Tuote(5, "Weihenstephaner Hefeweisse", 4), 15);
    }
}
