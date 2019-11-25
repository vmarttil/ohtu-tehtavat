
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
    	luoLukujono(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
        	throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla negatiivinen");
        }
        luoLukujono(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei voi olla negatiivinen");
        }
        luoLukujono(kapasiteetti, kasvatuskoko);
    }

    private void luoLukujono(int kapasiteetti, int kasvatuskoko) {
    	this.lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            this.lukujono[alkioidenLkm] = luku;
            this.alkioidenLkm++;
            if (alkioidenLkm == lukujono.length) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
    	if (lukuLoytyyJoukonKohdasta(luku) == -1) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    private int lukuLoytyyJoukonKohdasta(int luku) {
        for (int kohta = 0; kohta < this.alkioidenLkm; kohta++) {
            if (luku == this.lukujono[kohta]) {
                return kohta;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int kohta = lukuLoytyyJoukonKohdasta(luku);
    	if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                this.lukujono[j] = this.lukujono[j + 1]; 
            	this.lukujono[this.alkioidenLkm] = 0;
            }
            this.alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kasvataTaulukkoa() {
    	int[] uusiLukujono = new int[this.alkioidenLkm + this.kasvatuskoko];
    	kopioiTaulukko(this.lukujono, uusiLukujono);
    	this.lukujono = uusiLukujono;
    }
    
    private void kopioiTaulukko(int[] vanhaTaulukko, int[] uusiTaulukko) {
        for (int i = 0; i < vanhaTaulukko.length; i++) {
            uusiTaulukko[i] = vanhaTaulukko[i];
        }

    }

    public int mahtavuus() {
        return this.alkioidenLkm;
    }


    @Override
    public String toString() {
        if (this.alkioidenLkm == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < this.alkioidenLkm - 1; i++) {
                tuotos += this.lukujono[i] + ", ";
            }
            tuotos += this.lukujono[this.alkioidenLkm - 1] + "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[this.alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = this.lukujono[i];
        }
        return taulu;
    }   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
    }
        
}
