/**
 * A test class for the Valorant class.
 * 
 * @author Tobias Ephron
 */

package games;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValorantTest {

    /** Unrated Tests */
    @ParameterizedTest
    @CsvSource({
        "20, 8, 7, true, 3",
        "18, 10, 3, true, 5",
        "20, 8, 7, false, 3",
        "20, 8, 3, false, 5",
        "10, 15, 7, true, 7",
        "10, 15, 3, true, 15",
        "10, 15, 7, false, 15",
        "10, 15, 3, false, 15"
    })
    public void calculatesUnratedReps(int kills, int deaths, int assists, boolean win, int expected) {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.UNRATED, "kills", kills, "deaths", deaths, "assists", assists);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, win);

        assertEquals(expected, val.calculateReps());
    }

    /** Competitive Tests */
    @ParameterizedTest
    @CsvSource({
        "20, 8, 7, true, 4",
        "18, 10, 3, true, 5",
        "20, 8, 7, false, 4",
        "20, 8, 3, false, 6",
        "10, 15, 7, true, 7",
        "10, 15, 3, true, 16",
        "10, 15, 7, false, 16",
        "10, 15, 3, false, 16"
    })
    public void calculatesCompetitiveReps(int kills, int deaths, int assists, boolean win, int expected) {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.COMPETITIVE, "kills", kills, "deaths", deaths, "assists", assists);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, win);

        assertEquals(expected, val.calculateReps());
    }

    /** Switftplay Tests */
    @ParameterizedTest
    @CsvSource({
        "6, 3, 3, true, 3",
        "6, 3, 3, false, 6"
    })
    public void calculatesSwiftplayReps(int kills, int deaths, int assists, boolean win, int expected) {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.SWIFTPLAY, "kills", kills, "deaths", deaths,
                "assists", assists);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, win);

        assertEquals(expected, val.calculateReps());
    }

    /** Spike Rush Tests */
    @ParameterizedTest
    @CsvSource({
        "6, 3, 3, true, 3",
        "6, 3, 3, false, 6"
    })
    public void calculatesSpikeRushReps(int kills, int deaths, int assists, boolean win, int expected) {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.SPIKE_RUSH, "kills", kills, "deaths", deaths,
                "assists", assists);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, win);

        assertEquals(expected, val.calculateReps());
    }
}
