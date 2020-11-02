/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author tkoukkar
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchPalauttaaOikeanPelaajan() {
        Player pelaaja = stats.search("Yzerman");
        
        assertEquals(42, pelaaja.getGoals());
    }
    
    @Test
    public void searchPalauttaaNullJosPelaajaaEiLoydy() {
        Player pelaaja = stats.search("Alves");
        
        assertNull(pelaaja);
    }
    
    @Test
    public void teamPalauttaaKaikkiParametrinaAnnetunJoukkueenPelaajat() {
        List pelaajat = stats.team("EDM");
        
        assertEquals(3, pelaajat.size());
    }
    
    @Test
    public void topScorersPalauttaaParametrinaAnnetunLuvunMukaisenMaaranPelaajia() {
        List<Player> kolmenKarki = stats.topScorers(2);
        
        assertEquals(3, kolmenKarki.size());
    }
    
    @Test
    public void topScorersPalauttaaPelaajatOikeassaJarjestyksessa() {
        List<Player> kolmenKarki = stats.topScorers(3);
        
        Player gretzky = kolmenKarki.get(0);
        
        assertEquals("Gretzky", gretzky.getName());
    }
}

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
