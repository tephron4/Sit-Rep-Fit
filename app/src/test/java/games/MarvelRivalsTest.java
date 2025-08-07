/**
 * A test class for the MarvelRivals class.
 * 
 * @author: Tobias Ephron
 */

package test.java.games;

import games.MarvelRivals;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MarvelRivalsTest {
    @Test
    public void calculatesWinReps() {
        Map<String, Object> statValues = Map.of("kills", 34, "deaths", 6, "assists", 11);

        MarvelRivals mr = new MarvelRivals(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(6, mr.calculateReps(), "Calculated reps should be 6 (equal to deaths)");
    }

    @Test
    public void calculatesLossReps() {
        Map<String, Object> statValues = Map.of("kills", 18, "deaths", 10, "assists", 6);

        MarvelRivals mr = new MarvelRivals(Timestamp.from(Instant.now()), statValues,
                false);

        assertEquals(20, mr.calculateReps(), "Calculated reps should be 20 (twice # of deaths)");
    }

    @Test
    public void calculateRepsNegDeaths_throwsError() {
        Map<String, Object> statValues = Map.of("kills", 20, "deaths", -4, "assists", 9);

        MarvelRivals mr = new MarvelRivals(Timestamp.from(Instant.now()), statValues, false);

        assertThrows(IllegalArgumentException.class, () -> mr.calculateReps(),
                "Negative deaths should cause an IllegalArgumentException");

    }
}