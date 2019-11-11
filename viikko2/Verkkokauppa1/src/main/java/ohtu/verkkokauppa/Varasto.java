package ohtu.verkkokauppa;

import java.util.*;

public class Varasto implements VarastoInt {

    private static VarastoInt instanssi;

    public static VarastoInt getInstance() {
        if (instanssi == null) {
            instanssi = new Varasto();
        }

        return instanssi;
    }
    
    private KirjanpitoInt kirjanpito;
    private HashMap<TuoteInt, Integer> saldot;  
    
    private Varasto() {
        kirjanpito = Kirjanpito.getInstance();
        saldot = new HashMap<TuoteInt, Integer>();
        alustaTuotteet();
    }
            
    @Override
	public TuoteInt haeTuote(int id){
        for (TuoteInt t : saldot.keySet()) {
            if ( t.getId()==id) return t;
        }
        
        return null;
    }

    @Override
	public int saldo(int id){
        return saldot.get(haeTuote(id));
    }
    
    @Override
	public void otaVarastosta(TuoteInt t){        
        saldot.put(t,  saldo(t.getId())-1 );
        kirjanpito.lisaaTapahtuma("otettiin varastosta "+t);
    }
    
    @Override
	public void palautaVarastoon(TuoteInt t){
        saldot.put(t,  saldo(t.getId())+1 );
        kirjanpito.lisaaTapahtuma("palautettiin varastoon "+t);
    }    
    
    private void alustaTuotteet() {
        saldot.put(new Tuote(1, "Koff Portteri", 3), 100);
        saldot.put(new Tuote(2, "Fink Bräu I", 1), 25);
        saldot.put(new Tuote(3, "Sierra Nevada Pale Ale", 5), 30);
        saldot.put(new Tuote(4, "Mikkeller not just another Wit", 7), 40);
        saldot.put(new Tuote(5, "Weihenstephaner Hefeweisse", 4), 15);
    }
}
