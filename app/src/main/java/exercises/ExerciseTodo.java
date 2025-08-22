/**
 * Defines an instance of an exercise to
 * do, with the count for the reps or
 * time to do the exercise for.
 * 
 * @author Tobias Ephron
 */

package exercises;

import exercises.Exercise.ExerciseType;

import java.sql.Timestamp;
import java.time.Instant;

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
        if (this.completedAt == null) {
            this.completedAt = Timestamp.from(Instant.now());
        }
    }

    public Timestamp getCompletedAt() {
        return this.completedAt;
    }

    public String getInstructionString() {
        String typeString = "";
        switch (this.exercise.getType()) {
            case ExerciseType.REPS:
                typeString = "reps";
                break;
            case ExerciseType.TIMED:
                typeString = "secs";
                break;
            default:
                typeString = "";
        }

        return String.format("Do %s for %d %s", this.exercise.getName(), this.count, typeString);
    }
}