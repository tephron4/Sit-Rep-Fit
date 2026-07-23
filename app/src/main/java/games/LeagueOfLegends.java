/**
 * Game class for League of Legends.
 *
 * @author Tobias Ephron
 */

package games;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LeagueOfLegends extends Game {

    @SuppressWarnings("rawtypes")
    private static LinkedHashMap<String, Class> stats = new LinkedHashMap<>() {
        {
            put("kills", Integer.class);
            put("deaths", Integer.class);
            put("assists", Integer.class);
            put("cs", Integer.class);
            put("game time - minutes", Integer.class);
            put("game time - seconds", Integer.class);
            put("vision", Integer.class);
        }
    };

    public LeagueOfLegends() {
        super();
    }

    public LeagueOfLegends(Timestamp endTime, Map<String, Object> statValues, boolean win) {
        super(endTime, statValues, win);
    }

    @Override
    public int calculateReps() {
        int kills = (int) this.getStatValue("kills");
        int deaths = (int) this.getStatValue("deaths");
        int assists = (int) this.getStatValue("assists");
        int cs = (int) this.getStatValue("cs");
        int gameMinutes = (int) this.getStatValue("game time - minutes");
        int gameSeconds = (int) this.getStatValue("game time - seconds");
        int vision = (int) this.getStatValue("vision");

        if (deaths < 0) {
            throw new IllegalArgumentException("Deaths (" + deaths + ") cannot be negative");
        }

        double totalGameMinutes = gameMinutes + (gameSeconds / 60.0);
        double csPerMinute = totalGameMinutes > 0 ? cs / totalGameMinutes : 0;

        int reps = Math.round(12f
                + deaths * 2f
                - kills * 0.5f
                - assists * 0.3f
                - csPerMinute * 0.6f
                - vision * 0.04f
                + (this.getWin() ? -1f : 1f));
        return Math.max(1, reps);
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
