/** 
 * A test class for the GetWorkout class.
 * 
 * @author: Tobias Ephron
 */

package workout_processing;

import java.util.Random;
import org.junit.jupiter.api.Test;

import exercises.Exercise;
import exercises.ExerciseTodo;
import games.Game;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetWorkoutTest {

    Game mockGame = mock(Game.class);

    @Test
    public void appGetsRepsExerciseTodo() {
        Random mockRandom = mock(Random.class);

        GetWorkout gw = new GetWorkout(mockRandom);
        when(mockRandom.nextInt(gw.getExercises().size())).thenReturn(0); // returns push-ups (REPS)

        int reps = 8;
        when(mockGame.calculateReps()).thenReturn(reps);

        ExerciseTodo winExerciseTodo = gw.getExerciseTodo(mockGame);

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
        when(mockGame.calculateReps()).thenReturn(reps);

        ExerciseTodo winExerciseTodo = gw.getExerciseTodo(mockGame);

        assertEquals("planks", winExerciseTodo.getExercise().getName());
        assertEquals(Exercise.ExerciseType.TIMED, winExerciseTodo.getExercise().getType());
        assertNull(winExerciseTodo.getCompletedAt());
        assertEquals(reps * 5, winExerciseTodo.getCount(), "workout should have count of 40");
    }
}