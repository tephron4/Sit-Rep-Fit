/**
 * Game class for Marvel Rivals.
 * 
 * @author Tobias Ephron
 */

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class MarvelRivals extends Game {

    public MarvelRivals(String name, Timestamp endTime, Map<String, Object> statValues, boolean win) {
        // Set the tracked stats
        List<String> statList = List.of("kills", "deaths", "assists");
        // TODO: Add other stats (i.e. final hits, damage, healing, damage blocked)

        super(name, endTime, statList, statValues, win);
    }

    @Override
    public int calculateReps() {
        int deaths = (int) this.statValues.get("deaths");

        if (deaths < 0) {
            throw new IllegalArgumentException("Deaths (" + deaths + ") cannot be negative");
        }

        return (deaths == 0 ? 1 : deaths) * (this.win ? 1 : 2);
    }
}