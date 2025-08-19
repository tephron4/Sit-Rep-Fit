/** 
 * A test class for the GetWorkout class.
 * 
 * @author: Tobias Ephron
 */

package test.java.workout_processing;

import workout_processing.GetWorkout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import org.junit.jupiter.api.Test;

import main.java.exercises.Exercise;
import main.java.exercises.ExerciseTodo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetWorkoutTest {
    @Test
    public void appGetsRepsExerciseTodo() {
        Random mockRandom = mock(Random.class);

        GetWorkout gw = new GetWorkout(mockRandom);
        when(mockRandom.nextInt(gw.getExercises().size())).thenReturn(0); // returns push-ups (REPS)
        int reps = 8;

        ExerciseTodo winExerciseTodo = gw.getExerciseTodo(reps);

        assertEquals("push-ups", winExerciseTodo.getExercise().getName());
        assertEquals(Exercise.ExerciseType.REPS, winExerciseTodo.getExercise().getType());
        assertNull(winExerciseTodo.getCompletedAt());
        assertEquals(reps, winExerciseTodo.getCount(), "workout should have count of 8");
    }

    @Test
    public void appGetsTimedExerciseTodo() {
        Random mockRandom = mock(Random.class);

        GetWorkout gw = new GetWorkout(mockRandom);
        when(mockRandom.nextInt(gw.getExercises().size())).thenReturn(1); // returns planks (Timed)
        int reps = 8;

        ExerciseTodo winExerciseTodo = gw.getExerciseTodo(reps);

        assertEquals("planks", winExerciseTodo.getExercise().getName());
        assertEquals(Exercise.ExerciseType.TIMED, winExerciseTodo.getExercise().getType());
        assertNull(winExerciseTodo.getCompletedAt());
        assertEquals(reps * 5, winExerciseTodo.getCount(), "workout should have count of 40");
    }
}