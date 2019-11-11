package ohtu.verkkokauppa;

public interface VarastoInt {

	TuoteInt haeTuote(int id);

	int saldo(int id);

	void otaVarastosta(TuoteInt t);

	void palautaVarastoon(TuoteInt t);

}