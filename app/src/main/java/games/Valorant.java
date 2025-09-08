
/**
 * Game class for Valorant
 * 
 * @author Tobias Ephron
 */

package games;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Valorant extends Game {

    private enum GameMode {
        UNRATED,
        COMPETITIVE,
        SWIFTPLAY,
        SPIKE_RUSH,
        DEATHMATCH,
        ESCALATION,
        TEAM_DEATHMATCH
    }

    @SuppressWarnings("rawtypes")
    private static LinkedHashMap<String, Class> stats = new LinkedHashMap<>() {
        {
            put("game mode", GameMode.class);
            put("kills", Integer.class);
            put("deaths", Integer.class);
            put("assists", Integer.class);
            // put("combat score", Integer.class);
            // put("econ rating", Integer.class);
            // put("first bloods", Integer.class);
            // put("plants", Integer.class);
            // put("defuses", Integer.class);
        }
    };

    public Valorant() {
        super();
    }

    public Valorant(Timestamp endTime, Map<String, Object> statValues, boolean win) {
        super(endTime, statValues, win);
    }

    @Override
    public int calculateReps() {
        GameMode gm = (GameMode) this.getStatValue("game mode");
        switch (gm) {
            case UNRATED:
                return this.getUnratedReps();
            case COMPETITIVE:
                return this.getCompetitiveReps();
            case SWIFTPLAY:
                return this.getSwiftplayReps();
            case SPIKE_RUSH:
                return this.getSpikeRushReps();
            case DEATHMATCH:
                return this.getDeathmatchReps();
            case ESCALATION:
                return this.getEscalationReps();
            case TEAM_DEATHMATCH:
                return this.getTeamDeathmatchReps();
            default:
                return -1;
        }
    }

    private int getUnratedReps() {
        int diff = this.getDiff();
        int assistModifier = Math.round((int) this.getStatValue("assists") * 0.1f);
        int count = 0;
        count += diff > 0 ? 1 : -1;
        count += assistModifier >= 1 ? 1 : -1;
        count += (boolean) this.getWin() ? 1 : -1;

        int starting = Math.round((int) this.getStatValue("deaths") * (2 / 3));

        if (count > 0) {
            int res = Math.round(starting * (2 / 3));
            return res;
        } else {
            int res = starting + (diff < 0 ? Math.abs(diff) : 0);
            return res;
        }
    }

    private int getCompetitiveReps() {
        int diff = this.getDiff();
        int assistModifier = Math.round((int) this.getStatValue("assists") * 0.1f);
        int count = 0;
        count += diff > 0 ? 1 : -1;
        count += assistModifier >= 1 ? 1 : -1;
        count += (boolean) this.getWin() ? 1 : -1;

        int starting = Math.round((int) this.getStatValue("deaths") * 0.75f);

        if (count > 0) {
            int res = Math.round(starting * (2 / 3));
            return res;
        } else {
            int res = starting + (diff < 0 ? Math.abs(diff) : 0);
            return res;
        }
    }

    private int getSwiftplayReps() {
        return (int) this.getStatValue("deaths") * (this.getWin() ? 1 : 2);
    }

    private int getSpikeRushReps() {
        return (int) this.getStatValue("deaths") * (this.getWin() ? 1 : 2);
    }

    private int getDeathmatchReps() {
        // TODO (ephront): Add deathmatch logic
        return 0;
    }

    private int getEscalationReps() {
        // TODO (ephront): Add escalation logic
        return 0;
    }

    private int getTeamDeathmatchReps() {
        // TODO (ephront): Add team deathmatch logic
        return 0;
    }

    private int getDiff() {
        return (int) this.getStatValue("kills") - (int) this.getStatValue("deaths");
    }

    @SuppressWarnings("rawtypes")
    @Override
    public LinkedHashMap<String, Class> getStats() {
        return stats;
    }

    @Override
    public List<String> getStatNames() {
        return new ArrayList<String>(stats.keySet());
    }
}