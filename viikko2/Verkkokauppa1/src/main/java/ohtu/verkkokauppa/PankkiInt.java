package ohtu.verkkokauppa;

public interface PankkiInt {

	boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);

}