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
    public void appGetsRepsWorkoutAfterWin() {
        Random mockRandom = mock(Random.class);

        GetWorkout gw = new GetWorkout(mockRandom);
        when(mockRandom.nextInt(gw.getWorkouts().size())).thenReturn(0); // returns push-ups (REPS)
        int deaths = 8;

        Workout winWorkout = gw.getWorkout(deaths, true);

        assertEquals(deaths, winWorkout.getCount(), "workout should have count of 8");
    }

    @Test
    public void appGetsTimedWorkoutAfterWin() {
        Random mockRandom = mock(Random.class);

        GetWorkout gw = new GetWorkout(mockRandom);
        when(mockRandom.nextInt(gw.getWorkouts().size())).thenReturn(1); // returns planks (Timed)
        int deaths = 9;

        Workout winWorkout = gw.getWorkout(deaths, true);

        assertEquals(deaths * 5, winWorkout.getCount(), "workout should have count of 40");
    }

    @Test
    public void appGetsRepsWorkoutAfterLoss() {
        Random mockRandom = mock(Random.class);

        GetWorkout gw = new GetWorkout(mockRandom);
        when(mockRandom.nextInt(gw.getWorkouts().size())).thenReturn(0); // returns push-ups (REPS)
        int deaths = 7;

        Workout lossWorkout = gw.getWorkout(deaths, false);

        assertEquals(deaths * 2, lossWorkout.getCount(), "workout should have count of 14");
    }

    @Test
    public void appGetsTimedWorkoutAfterLoss() {
        Random mockRandom = mock(Random.class);

        GetWorkout gw = new GetWorkout(mockRandom);
        when(mockRandom.nextInt(gw.getWorkouts().size())).thenReturn(1); // returns planks (TIMED)
        int deaths = 6;

        Workout lossWorkout = gw.getWorkout(deaths, false);

        assertEquals((deaths * 5) * 2, lossWorkout.getCount(), "workout should have count of 60");
    }
}