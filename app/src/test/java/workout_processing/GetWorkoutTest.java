/** 
 * A test class for the GetWorkout class.
 * 
 * @author: Tobias Ephron
 */

package test.java.workout_processing;

import workout_processing.GetWorkout;
import workout_processing.GetWorkout.Workout;

import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetWorkoutTest {
    @Test
    public void appGetsRepsWorkout() {
        Random mockRandom = mock(Random.class);

        GetWorkout gw = new GetWorkout(mockRandom);
        when(mockRandom.nextInt(gw.getWorkouts().size())).thenReturn(0); // returns push-ups (REPS)
        int reps = 8;

        Workout winWorkout = gw.getWorkout(reps);

        assertEquals(reps, winWorkout.getCount(), "workout should have count of 8");
    }

    @Test
    public void appGetsTimedWorkout() {
        Random mockRandom = mock(Random.class);

        GetWorkout gw = new GetWorkout(mockRandom);
        when(mockRandom.nextInt(gw.getWorkouts().size())).thenReturn(1); // returns planks (Timed)
        int reps = 9;

        Workout winWorkout = gw.getWorkout(reps);

        assertEquals(reps * 5, winWorkout.getCount(), "workout should have count of 40");
    }
}