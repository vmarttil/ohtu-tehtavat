package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        vanhaArvo = sovellus.tulos();
        sovellus.nollaa();
        syotekentta.setText("");
        tuloskentta.setText(String.valueOf(sovellus.tulos()));
    }
    
    @Override
    public void peru() {
        sovellus.plus(vanhaArvo);
        syotekentta.setText("");
        tuloskentta.setText(String.valueOf(sovellus.tulos()));
    }
}