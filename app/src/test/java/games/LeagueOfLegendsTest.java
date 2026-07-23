/**
 * A test class for the LeagueOfLegends class.
 *
 * @author Tobias Ephron
 */

package games;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeagueOfLegendsTest {
    @Test
    public void calculatesWinReps() {
        Map<String, Object> statValues = Map.of(
                "kills", 12,
                "deaths", 3,
                "assists", 5,
                "cs", 100,
                "game time - minutes", 10,
                "game time - seconds", 0,
                "vision", 20);

        LeagueOfLegends lol = new LeagueOfLegends(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(3, lol.calculateReps(), "Good performance should reduce the rep count");
    }

    @Test
    public void calculatesLossReps() {
        Map<String, Object> statValues = Map.of(
                "kills", 10,
                "deaths", 4,
                "assists", 3,
                "cs", 90,
                "game time - minutes", 15,
                "game time - seconds", 0,
                "vision", 15);

        LeagueOfLegends lol = new LeagueOfLegends(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(11, lol.calculateReps(), "Poor performance should increase the rep count");
    }

    @Test
    public void constructorWithDiffStatValues_throwsException() {
        Map<String, Object> statValues = Map.of(
                "kills", 10,
                "extraStat", "fails",
                "deaths", 2,
                "game time - minutes", 10,
                "game time - seconds", 0);

        assertThrows(IllegalArgumentException.class,
                () -> new LeagueOfLegends(Timestamp.from(Instant.now()), statValues, true));
    }
}
