
/**
 * A general game class.
 *
 * @author Tobias Ephron
 */

package games;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Game {

    protected final String name;
    protected final Timestamp endTime;
    protected List<String> stats;
    protected Map<String, Object> statValues;
    protected boolean win;

    public Game(
            String name,
            Timestamp endTime,
            List<String> stats,
            Map<String, Object> statValues,
            boolean win) {
        this.name = name;
        this.endTime = endTime;
        this.stats = stats;
        
        if (checkStatValues(statValues)) {}

        this.win = win;
    }

    private boolean checkStatValues(Map<String, Object> map) {
        return this.stats.size() == map.keySet().size() && this.stats.containsAll(map.keySet());
    }

    public String getName() {
        return this.name;
    }

    public Timestamp getEndTime() {
        return this.endTime;
    }

    public List<String> getStats() {
        return this.stats;
    }

    public Map<String, Object> getStatValues() {
        return this.statValues;
    }

    public Map<String, Object> getStatsValues(List<String> keys) {
        return this.statValues.entrySet()
                .stream()
                .filter(entry -> keys.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setStatValue(String key, Object value) {
        this.statValues.put(key, value);
    }

    /**
     * Calculate the number of reps of a workout to do for the game.
     *
     * @return the number of reps
     */
    public abstract int calculateReps();
}
