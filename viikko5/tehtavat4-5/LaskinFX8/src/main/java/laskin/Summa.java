package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    
    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(this.syotekentta.getText());
        } catch (Exception e) {
        }
        vanhaArvo = sovellus.tulos();
        sovellus.plus(arvo);
        syotekentta.setText("");
        tuloskentta.setText(String.valueOf(sovellus.tulos()));
    }  
    
    @Override
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(vanhaArvo);
        syotekentta.setText("");
        tuloskentta.setText(String.valueOf(sovellus.tulos()));
    }
}