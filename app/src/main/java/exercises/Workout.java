/**
 * Defines a workout (group of exercises)
 * 
 * @author Tobias Ephron
 */

package main.java.exercises;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class Workout {

    private List<Exercise> exercises;
    private Timestamp completedAt;

    public Workout() {
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public List<Exercise> getExercises() {
        return this.exercises;
    }

    public void complete() {
        this.completedAt = Timestamp.from(Instant.now());
    }

    public Timestamp getCompletedAt() {
        return this.completedAt;
    }
}