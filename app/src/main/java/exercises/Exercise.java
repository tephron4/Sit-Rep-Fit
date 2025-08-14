/**
 * Defines an exercise for a workout.
 * 
 * @author Tobias Ephron
 */

package main.java.exercises;

import java.sql.Timestamp;
import java.time.Instant;

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

/**
 * Defines an instance of an exercise to
 * do, with the count for the reps or
 * time to do the exercise for.
 * 
 * @author Tobias Ephron
 */
public class ExerciseTodo {

    private final Exercise exercise;
    private final int count;
    private Timestamp completedAt;

    public ExerciseTodo(Exercise exercise, int count) {
        this.exercise = exercise;
        this.count = count;
    }

    public Exercise getExercise() {
        return this.exercise;
    }

    public int getCount() {
        return this.count;
    }

    public void complete() {
        this.completedAt = Timestamp.from(Instant.now());
    }

    public String getInstructionString() {
        String typeString = "";
        switch (this.exercise.getType()) {
            case ExerciseType.REPS:
                typeString = reps;
            case ExerciseType.TIMED:
                typeString = secs;
            default:
                typeString = "";
        }

        return String.format("Do %s for %d %s", this.exercise.getName(), this.count, typeString);
    }
}
