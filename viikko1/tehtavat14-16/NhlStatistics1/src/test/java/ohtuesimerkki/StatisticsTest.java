package ohtuesimerkki;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


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
    public void playerSearchWorks() {
    	Player player = stats.search("Kurri");
        assertEquals("Kurri", player.getName());
    }
    
    @Test
    public void nonexistentPlayerSearchDoesNotWork() {
    	Player player = stats.search("None");
        assertEquals(null, player);
    }
    
    @Test
    public void teamSearchWorks() {
    	List<Player> team = stats.team("EDM");
        assertEquals(3, team.size());
    }
    
    @Test
    public void topScorersSearchWorks() {
    	List<Player> topScorers = stats.topScorers(1);
        assertEquals("Gretzky", topScorers.get(0).getName());
    }
    
    
}