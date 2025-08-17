/**
 * Test class for the GetGame class
 * 
 * @author Tobias Ephron
 */

package test.java.workout_processing;

import games.Game;
import main.java.workout_processing.GetGame;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GetGameTest {

    @Test
    public void getsWonGameWithStats() {
        String input = "1\ny\n25\n7\n10";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        GetGame getGame = new GetGame(new Scanner(in));

        Game actualGame = getGame.getGame();

        assertNotNull(actualGame.getEndTime());
        assertTrue(actualGame.getWin());
        assertEquals(Map.of("kills", 25, "deaths", 7, "assists", 10), actualGame.getStatValues());
    }

    @Test
    public void getsLostGameWithStats() {
        String input = "1\nn\n19\n12\n5";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        GetGame getGame = new GetGame(new Scanner(in));

        Game actualGame = getGame.getGame();

        assertNotNull(actualGame.getEndTime());
        assertFalse(actualGame.getWin());
        assertEquals(Map.of("kills", 19, "deaths", 12, "assists", 5), actualGame.getStatValues());
    }
}
