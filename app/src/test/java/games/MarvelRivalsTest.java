/**
 * A test class for the MarvelRivals class.
 * 
 * @author: Tobias Ephron
 */

package games;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MarvelRivalsTest {
    @Test
    public void calculatesWinReps() {
        Map<String, Object> statValues = Map.of("kills", 34, "deaths", 6, "assists", 11);

        MarvelRivals mr = new MarvelRivals(Timestamp.from(Instant.now()), MarvelRivalsMode.UNKNOWN_MODE, statValues, true);

        assertEquals(6, mr.calculateReps(), "Calculated reps should be 6 (equal to deaths)");
    }

    @Test
    public void calculatesLossReps() {
        Map<String, Object> statValues = Map.of("kills", 18, "deaths", 10, "assists", 6);

        MarvelRivals mr = new MarvelRivals(Timestamp.from(Instant.now()), MarvelRivalsMode.UNKNOWN_MODE, statValues,
                false);

        assertEquals(20, mr.calculateReps(), "Calculated reps should be 20 (twice # of deaths)");
    }

    @Test
    public void constructor_shouldNotThrowException() {
        Map<String, Object> statValues = Map.of("kills", 34, "deaths", 6, "assists", 11);

        assertDoesNotThrow(() -> new MarvelRivals(Timestamp.from(Instant.now()), MarvelRivalsMode.UNKNOWN_MODE, statValues, true));
    }

    @Test
    public void constructorWithDiffLengthStatValues_throwsException() {
        Map<String, Object> shortStatValues = Map.of("kills", 40, "assists", 13);
        Map<String, Object> longStatValues = Map.of("kills", 40, "assists", 13, "deaths", 10, "extraStat", "fails");

        assertThrows(IllegalArgumentException.class,
                () -> new MarvelRivals(Timestamp.from(Instant.now()), MarvelRivalsMode.UNKNOWN_MODE, shortStatValues, true));
        assertThrows(IllegalArgumentException.class,
                () -> new MarvelRivals(Timestamp.from(Instant.now()), MarvelRivalsMode.UNKNOWN_MODE, longStatValues, true));
    }

    @Test
    public void constructorWithDiffStatValues_throwsException() {
        Map<String, Object> statValues = Map.of("kills", 40, "extraStat", "fails", "deaths", 10);

        assertThrows(IllegalArgumentException.class,
                () -> new MarvelRivals(Timestamp.from(Instant.now()), MarvelRivalsMode.UNKNOWN_MODE, statValues, true));
    }
}