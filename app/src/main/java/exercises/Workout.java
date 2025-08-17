/**
 * Defines a workout (group of exercises)
 * 
 * @author Tobias Ephron
 */

package main.java.exercises;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Workout {

    private List<ExerciseTodo> exercises;
    private Timestamp completedAt;

    public Workout() {
        this.exercises = new ArrayList<ExerciseTodo>();
    }

    public void addExercise(ExerciseTodo exercise) {
        this.exercises.add(exercise);
    }

    public List<ExerciseTodo> getExercises() {
        return this.exercises;
    }

    public void complete() {
        if (this.completedAt == null) {
            this.completedAt = Timestamp.from(Instant.now());
        }
    }

    public Timestamp getCompletedAt() {
        return this.completedAt;
    }
}