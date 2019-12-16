/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

/**
 *
 * @author vmarttil
 */
public interface IO {
    public String kysyPelimuoto();
    public String lueSiirto(String kysymys);
    public void tulosta(String tuloste);
}
