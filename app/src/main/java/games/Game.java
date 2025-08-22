
/**
 * A general game class.
 *
 * @author Tobias Ephron
 */

package games;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public abstract class Game {

    protected final Timestamp endTime;
    protected Map<String, Object> statValues;
    protected boolean win;

    public Game() {
        this.endTime = Timestamp.from(Instant.now());
    }

    public Game(
            Timestamp endTime,
            Map<String, Object> statValues,
            boolean win) {
        this.endTime = endTime;

        if (!checkStatValues(statValues)) {
            throw new IllegalArgumentException("Given statValues: " + statValues.toString()
                    + " do not match the expected stats: " + getStatNames().toString());
        }
        this.statValues = statValues;

        this.win = win;
    }

    private boolean checkStatValues(Map<String, Object> map) {
        if (getStatNames().size() == map.keySet().size() && getStatNames().containsAll(map.keySet())) {
            @SuppressWarnings("rawtypes")
            LinkedHashMap<String, Class> typesMap = getStats();
            for (@SuppressWarnings("rawtypes")
            Entry entry : map.entrySet()) {
                if (typesMap.get(entry.getKey()) != entry.getValue().getClass()) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public Timestamp getEndTime() {
        return this.endTime;
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

    public Object getStatValue(String key) {
        return this.statValues.get(key);
    }

    public void setStatValue(String key, Object value) {
        this.statValues.put(key, value);
    }

    public boolean getWin() {
        return this.win;
    }

    public void setWin(boolean w) {
        this.win = w;
    }

    /**
     * Calculate the number of reps of a workout to do for the game.
     * 
     * Each game will have it's own implementation to account for
     * the different statistics and their differing importance.
     *
     * @return the number of reps
     */
    public abstract int calculateReps();

    /**
     * Function for getting the map of stats to their
     * respective types.
     * 
     * @return a map of stats to their respective types
     */
    @SuppressWarnings("rawtypes")
    public abstract LinkedHashMap<String, Class> getStats();

    /**
     * Function for getting the list of stats supported for
     * a Game class.
     * 
     * @return a list of the supported stats for a Game class
     */
    public abstract List<String> getStatNames();
}
