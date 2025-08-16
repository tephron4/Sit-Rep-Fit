/**
 * Tests for the Exercise class
 * 
 * @author Tobias Ephron
 */

package test.java.exercises;

import main.java.exercises.Exercise;
import main.java.exercises.Exercise.ExerciseType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {
    @Test
    public void getsName() {
        Exercise exercise = new Exercise("test_1", ExerciseType.REPS);

        assertEquals("test_1", exercise.getName());
    }

    @Test
    public void getsRepsType() {
        Exercise exercise = new Exercise("test_1", ExerciseType.REPS);

        assertEquals(ExerciseType.REPS, exercise.getType());
    }

    @Test
    public void getsTimedType() {
        Exercise exercise = new Exercise("test_2", ExerciseType.TIMED);

        assertEquals(ExerciseType.TIMED, exercise.getType());
    }
}