
/**
 * Game class for Marvel Rivals.
 *
 * @author Tobias Ephron
 */

package games;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarvelRivals extends Game<MarvelRivalsMode> {

    @SuppressWarnings("rawtypes")
    private static LinkedHashMap<String, Class> stats = new LinkedHashMap<>() {
        {
            put("kills", Integer.class);
            put("deaths", Integer.class);
            put("assists", Integer.class);
        }
    };

    public MarvelRivals() {
        super(MarvelRivalsMode.UNKNOWN_MODE);
    }

    public MarvelRivals(Timestamp endTime, MarvelRivalsMode gameMode, Map<String, Object> statValues, boolean win) {
        super(endTime, gameMode, statValues, win);
    }

    @Override
    public int calculateReps() {
        int deaths = (int) this.statValues.get("deaths");

        return (deaths == 0 ? 1 : deaths) * (this.win ? 1 : 2);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public LinkedHashMap<String, Class> getStats() {
        return stats;
    }

    @Override
    public List<String> getStatNames() {
        // TODO: Add other stats (i.e. final hits, damage, healing, damage blocked)
        return new ArrayList<String>(stats.keySet());
    }
}
