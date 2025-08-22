/**
 * Defines an exercise for a workout.
 * 
 * @author Tobias Ephron
 */

package exercises;

public class Exercise {

    /** Defines if the exercise is rep or time based */
    public enum ExerciseType {
        REPS,
        TIMED
    }

    private final String name;
    private final ExerciseType type;

    public Exercise(String name, ExerciseType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public ExerciseType getType() {
        return this.type;
    }
}
