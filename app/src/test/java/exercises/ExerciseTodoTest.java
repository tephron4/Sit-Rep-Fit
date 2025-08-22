/**
 * Tests for the ExerciseTodo class
 * 
 * @author Tobias Ephron
 */

package exercises;

import exercises.Exercise.ExerciseType;

import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ExerciseTodoTest {

    private static Exercise exercise_reps_1 = new Exercise("exercise_1", ExerciseType.REPS);
    private static Exercise exercise_timed_1 = new Exercise("exercise_2", ExerciseType.TIMED);

    @Test
    public void getsExercise() {
        ExerciseTodo eTodo = new ExerciseTodo(exercise_reps_1, 5);

        assertEquals(exercise_reps_1, eTodo.getExercise());
    }

    @Test
    public void getsCount() {
        ExerciseTodo eTodo = new ExerciseTodo(exercise_reps_1, 5);

        assertEquals(5, eTodo.getCount());
    }

    @Test
    public void canComplete() {
        ExerciseTodo eTodo = new ExerciseTodo(exercise_reps_1, 5);

        eTodo.complete();
        Timestamp completedAt = eTodo.getCompletedAt();

        assertNotNull(completedAt);
    }

    @Test
    public void alreadyCompleted() {
        ExerciseTodo eTodo = new ExerciseTodo(exercise_reps_1, 5);
        eTodo.complete();

        Timestamp initialCompletedAt = eTodo.getCompletedAt();
        assertNotNull(initialCompletedAt);

        eTodo.complete(); // Call complete() again

        assertEquals(initialCompletedAt, eTodo.getCompletedAt()); // completedAt value should not have changed
    }

    @Test
    public void getsRepsInstructionString() {
        ExerciseTodo eTodo = new ExerciseTodo(exercise_reps_1, 5);

        String expectedInstruction = "Do exercise_1 for 5 reps";

        assertEquals(expectedInstruction, eTodo.getInstructionString());
    }

    @Test
    public void getsTimedInstructionString() {
        ExerciseTodo eTodo = new ExerciseTodo(exercise_timed_1, 40);

        String expectedInstruction = "Do exercise_2 for 40 secs";

        assertEquals(expectedInstruction, eTodo.getInstructionString());
    }
}
