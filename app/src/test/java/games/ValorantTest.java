/**
 * A test class for the Valorant class.
 * 
 * @author Tobias Ephron
 */

package games;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValorantTest {


    /** Unrated Tests */
    @Test
    public void calculatesUnratedReps_Pos_AssistMod_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.UNRATED, "kills", 20, "deaths", 8,
                "assists", 7);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(3, val.calculateReps());
    }

    @Test
    public void calculatesUnratedReps_Pos_NoAssistMod_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.UNRATED, "kills", 18, "deaths", 10,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(5, val.calculateReps());
    }

    @Test
    public void calculatesUnratedReps_Pos_AssistMod_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.UNRATED, "kills", 20, "deaths", 8,
                "assists", 7);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(3, val.calculateReps());
    }

    @Test
    public void calculatesUnratedReps_Pos_NoAssistMod_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.UNRATED, "kills", 20, "deaths", 8,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(5, val.calculateReps());
    }

    @Test
    public void calculatesUnratedReps_Neg_AssistMod_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.UNRATED, "kills", 10, "deaths", 15,
                "assists", 7);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(7, val.calculateReps());
    }

    @Test
    public void calculatesUnratedReps_Neg_NoAssistMod_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.UNRATED, "kills", 10, "deaths", 15,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(15, val.calculateReps());
    }

    @Test
    public void calculatesUnratedReps_Neg_AssistMod_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.UNRATED, "kills", 10, "deaths", 15,
                "assists", 7);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(15, val.calculateReps());
    }

    @Test
    public void calculatesUnratedReps_Neg_NoAssistMod_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.UNRATED, "kills", 10, "deaths", 15,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(15, val.calculateReps());
    }

    /** Competitive Tests */
    @Test
    public void calculatesCompetitiveReps_Pos_AssistMod_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.COMPETITIVE, "kills", 20, "deaths", 8,
                "assists", 7);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(4, val.calculateReps());
    }

    @Test
    public void calculatesCompetitiveReps_Pos_NoAssistMod_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.COMPETITIVE, "kills", 18, "deaths", 10,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(5, val.calculateReps());
    }

    @Test
    public void calculatesCompetitiveReps_Pos_AssistMod_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.COMPETITIVE, "kills", 20, "deaths", 8,
                "assists", 7);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(4, val.calculateReps());
    }

    @Test
    public void calculatesCompetitiveReps_Pos_NoAssistMod_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.COMPETITIVE, "kills", 20, "deaths", 8,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(6, val.calculateReps());
    }

    @Test
    public void calculatesCompetitiveReps_Neg_AssistMod_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.COMPETITIVE, "kills", 10, "deaths", 15,
                "assists", 7);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(7, val.calculateReps());
    }

    @Test
    public void calculatesCompetitiveReps_Neg_NoAssistMod_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.COMPETITIVE, "kills", 10, "deaths", 15,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(16, val.calculateReps());
    }

    @Test
    public void calculatesCompetitiveReps_Neg_AssistMod_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.COMPETITIVE, "kills", 10, "deaths", 15,
                "assists", 7);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(16, val.calculateReps());
    }

    @Test
    public void calculatesCompetitiveReps_Neg_NoAssistMod_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.COMPETITIVE, "kills", 10, "deaths", 15,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(16, val.calculateReps());
    }

    /** Switftplay Tests */
    @Test
    public void calculatesSwiftplayReps_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.SWIFTPLAY, "kills", 6, "deaths", 3,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(3, val.calculateReps());
    }

    @Test
    public void calculatesSwiftplayReps_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.SWIFTPLAY, "kills", 6, "deaths", 3,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(6, val.calculateReps());
    }

    /** Spike Rush Tests */
    @Test
    public void calculatesSpikeRushReps_Win() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.SPIKE_RUSH, "kills", 6, "deaths", 3,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, true);

        assertEquals(3, val.calculateReps());
    }

    @Test
    public void calculatesSpikeRushReps_Loss() {
        Map<String, Object> statValues = Map.of("game mode", Valorant.GameMode.SPIKE_RUSH, "kills", 6, "deaths", 3,
                "assists", 3);

        Valorant val = new Valorant(Timestamp.from(Instant.now()), statValues, false);

        assertEquals(6, val.calculateReps());
    }
}
