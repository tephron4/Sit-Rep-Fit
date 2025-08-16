/**
 * Tests for the ExerciseTodo class
 * 
 * @author Tobias Ephron
 */

package test.java.exercises;

import main.java.exercises.Exercise;
import main.java.exercises.ExerciseTodo;
import main.java.exercises.Exercise.ExerciseType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import java.sql.Timestamp;
import java.time.Instant;

public class ExerciseTodoTest {

    private Exercise exercise_reps_1 = new Exercise("exercise_1", ExerciseType.REPS);

    @Test
    public void getsExercise() {
        ExerciseTodo eTodo = new ExerciseTodo(exercise_reps_1, 5);

        assertEquals(exercise_reps_1, eTodo.getExercise());
    }

    @Test
    public void getsCount() {
        ExerciseTodo eTodo = new ExerciseTodo(exercise_reps_1, 5);

        asserEquals(5, eTodo.getCount());
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
    public void getsCompletedAt() {
        ExerciseTodo eTodo = new ExerciseTodo(exercise_reps_1, 5);
        Instant fixedInstant = Instant.now();

        try (MockedStatic<Instant> mockedStaticInstant = mockStatic(Instant.class)) {
            mockedStaticInstant.when(Instant::now).thenReturn(fixedInstant);

            eTodo.complete();

            Timestamp expected = Timestamp.from(fixedInstant);

            assertEquals(expected, etodo.getCompletedAt());
        }
    }
}
