package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjanpitoInt {

	void lisaaTapahtuma(String tapahtuma);

	ArrayList<String> getTapahtumat();

}