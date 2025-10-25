/**
 * Tests for the Workout class
 * 
 * @author Tobias Ephron
 */

package exercises;

import exercises.Exercise.ExerciseType;
import games.Game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.List;

public class WorkoutTest {

    private Exercise exercise_1 = new Exercise("exercise_1", ExerciseType.REPS);
    private Exercise exercise_2 = new Exercise("exercise_2", ExerciseType.TIMED);

    @SuppressWarnings("rawtypes")
    Game mockGame = mock(Game.class);

    private ExerciseTodo eTodo_1 = new ExerciseTodo(exercise_1, mockGame);
    private ExerciseTodo eTodo_2 = new ExerciseTodo(exercise_2, mockGame);

    @Test
    public void addsExerciseToWorkout() {
        Workout workout = new Workout();

        // check that it starts with no exercises (ExerciseTodos)
        assertEquals(List.of(), workout.getExercises());

        // add exercises
        workout.addExercise(eTodo_1);
        workout.addExercise(eTodo_2);

        // check for exercises
        assertEquals(List.of(eTodo_1, eTodo_2), workout.getExercises());
    }

    @Test
    public void canComplete() {
        Workout workout = new Workout();

        // check that completedAt is null
        assertNull(workout.getCompletedAt());

        // run complete()
        workout.complete();

        // check that completedAt is not null
        assertNotNull(workout.getCompletedAt());
    }

    @Test
    public void alreadyCompleted() {
        Workout workout = new Workout();
        workout.complete();

        Timestamp initialCompletedAt = workout.getCompletedAt();
        assertNotNull(initialCompletedAt);

        workout.complete(); // try to complete again

        assertEquals(initialCompletedAt, workout.getCompletedAt());
    }
}
