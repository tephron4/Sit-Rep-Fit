/**
 * Test class for the GetGame class
 * 
 * @author Tobias Ephron
 */

package test.java.workout_processing;

import games.Game;
import main.java.workout_processing.GetGame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GetGameTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to a ByteArrayOutputStream
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreSystemOut() {
        // Restore the original System.out after each test
        System.setOut(originalOut);
    }

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

    @Test
    public void handlesNonIntegerGameChoice() {
        String input = "test\n1\ny\n25\n7\n10";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        GetGame getGame = new GetGame(new Scanner(in));

        Game actualGame = getGame.getGame();

        String invalidInput = "Please input a valid integer from the list of games";

        // Check that it caught the invalid input
        assertTrue(outputStream.toString().contains(invalidInput));
        // Check that the game was still retrieved correctly
        assertNotNull(actualGame.getEndTime());
        assertTrue(actualGame.getWin());
        assertEquals(Map.of("kills", 25, "deaths", 7, "assists", 10), actualGame.getStatValues());
    }

    @Test
    public void handlesInvalidGameChoice() {
        String input = "4444\n1\ny\n25\n7\n10";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        GetGame getGame = new GetGame(new Scanner(in));

        Game actualGame = getGame.getGame();

        String invalidInput = "Please select an option from the given list";

        // Check that it caught the invalid input
        assertTrue(outputStream.toString().contains(invalidInput));
        // Check that the game was still retrieved correctly
        assertNotNull(actualGame.getEndTime());
        assertTrue(actualGame.getWin());
        assertEquals(Map.of("kills", 25, "deaths", 7, "assists", 10), actualGame.getStatValues());
    }

    @Test
    public void handlesEmptyWinResponse() {
        String input = "1\n\ny\n25\n7\n10";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        GetGame getGame = new GetGame(new Scanner(in));

        Game actualGame = getGame.getGame();

        String invalidInput = "Please enter Yes/no (Y/n)";

        // Check that it caught the invalid input
        assertTrue(outputStream.toString().contains(invalidInput));
        // Check that the game was still retrieved correctly
        assertNotNull(actualGame.getEndTime());
        assertTrue(actualGame.getWin());
        assertEquals(Map.of("kills", 25, "deaths", 7, "assists", 10), actualGame.getStatValues());
    }

    @Test
    public void handlesInvalidWinResponse() {
        String input = "1\ntest\n1234\ny\n25\n7\n10";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        GetGame getGame = new GetGame(new Scanner(in));

        Game actualGame = getGame.getGame();

        String invalidInput = "Please answer with Yes/no (Y/n)";

        // Check that it caught the invalid input
        assertTrue(outputStream.toString().contains(invalidInput));
        // Check that the game was still retrieved correctly
        assertNotNull(actualGame.getEndTime());
        assertTrue(actualGame.getWin());
        assertEquals(Map.of("kills", 25, "deaths", 7, "assists", 10), actualGame.getStatValues());
    }
}
