
/**
 * Game class for Marvel Rivals.
 *
 * @author Tobias Ephron
 */

package games;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarvelRivals extends Game {

    private static Map<String, Class> stats = Map.of("kills", Integer.class, "deaths", Integer.class, "assists",
            Integer.class);

    public MarvelRivals() {
        super();
    }

    public MarvelRivals(Timestamp endTime, Map<String, Object> statValues, boolean win) {
        super(endTime, statValues, win);
    }

    @Override
    public int calculateReps() {
        int deaths = (int) this.statValues.get("deaths");

        if (deaths < 0) {
            throw new IllegalArgumentException("Deaths (" + deaths + ") cannot be negative");
        }

        return (deaths == 0 ? 1 : deaths) * (this.win ? 1 : 2);
    }

    @Override
    public Map<String, Class> getStats() {
        return stats;
    }

    @Override
    public List<String> getStatNames() {
        // TODO: Add other stats (i.e. final hits, damage, healing, damage blocked)
        return new ArrayList(stats.keySet());
    }
}
