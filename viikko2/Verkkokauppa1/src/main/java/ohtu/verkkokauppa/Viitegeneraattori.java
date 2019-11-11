package ohtu.verkkokauppa;

public class Viitegeneraattori implements ViitegeneraattoriInt {

    private static ViitegeneraattoriInt instanssi;

    public static ViitegeneraattoriInt getInstance() {
        if (instanssi == null) {
            instanssi = new Viitegeneraattori();
        }

        return instanssi;
    }
    
    private int seuraava;
    
    private Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
	public int uusi(){
        return seuraava++;
    }
}
