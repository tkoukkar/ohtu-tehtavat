/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author tkoukkar
 */
public class All implements Matcher {

    @Override
    public boolean matches(Player p) {
        return true;
    }
}
