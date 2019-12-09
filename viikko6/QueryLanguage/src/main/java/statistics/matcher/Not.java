/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author vmarttil
 */
public class Not implements Matcher {
    Matcher m;
    
    public Not(Matcher m) {
        this.m = m;
    }
    
    @Override
    public boolean matches(Player p) {
        if (this.m.matches(p) == true) {
            return false;
        } else {
            return true;
        }
    }
}
