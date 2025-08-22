/**
 * GetWorkout is the main class for getting the workout based on the user's
 * in-game performance.
 *
 * @author Tobias Ephron
 */
package workout_processing;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import games.Game;
import exercises.Exercise;
import exercises.ExerciseTodo;
import exercises.Workout;
import exercises.Exercise.ExerciseType;
import workout_processing.GetGame;

public class GetWorkout {

    private final Scanner scanner;
    private Random random;
    private List<Exercise> exercises;
    private Workout workout;

    public int winCounter;
    public int lossCounter;
    public int killCounter;
    public int deathCounter;
    public int assistCounter;

    public GetWorkout(Random random) {
        this.scanner = new Scanner(System.in);
        this.random = random;

        this.exercises = List.of(
                new Exercise("push-ups", ExerciseType.REPS),
                new Exercise("planks", ExerciseType.TIMED),
                new Exercise("sit-ups", ExerciseType.REPS),
                new Exercise("crunches", ExerciseType.REPS),
                new Exercise("wall-sit", ExerciseType.TIMED),
                new Exercise("lunges", ExerciseType.REPS),
                new Exercise("squats", ExerciseType.REPS));

        this.workout = new Workout();

        this.winCounter = 0;
        this.lossCounter = 0;
        this.killCounter = 0;
        this.deathCounter = 0;
        this.assistCounter = 0;
    }

    public List<Exercise> getExercises() {
        return this.exercises;
    }

    /**
     * Get the ExerciseTodo
     *
     * @param reps number of reps
     * @return the workout to do, with an updated count
     */
    public ExerciseTodo getExerciseTodo(int reps) {
        Exercise exercise = this.exercises.get(this.random.nextInt(this.exercises.size()));

        ExerciseTodo todo = new ExerciseTodo(
                exercise,
                exercise.getType() == ExerciseType.REPS ? reps : reps * 5);

        return todo;
    }

    // Prints a welcome message in the terminal
    private static void printWelcome() {
        System.out.println("\n\n\n\n");
        System.out.println("=====================================");
        System.out.println(" Welcome to the Sit-Rep Fit program! ");
        System.out.println("");
        System.out.println(" Let's stay active while getting W's ");
        System.out.println("=====================================");
        System.out.println("\n");
    }

    // Gets whether or not the user has a new game to report through the terminal
    private boolean newGame() {
        while (true) {
            System.out.println("\n\nNew game to report? (Y/n)");

            String resp = this.scanner.nextLine();

            if (resp.isEmpty()) {
                System.out.println("\nPlease enter Yes/no (Y/n)");
                continue;
            }

            switch (resp.toUpperCase().charAt(0)) {
                case 'Y' -> {
                    return true;
                }
                case 'N' -> {
                    return false;
                }
                default -> {
                    System.out.println("Please only answer with Y or n (Yes or no)");
                }
            }
        }
    }

    // Prints a goodbye message in the terminal
    private void printGoodbye() {
        int totalGames = this.winCounter + this.lossCounter;
        float winPercentage = ((float) this.winCounter / totalGames) * 100;
        float kdr = ((float) this.killCounter / (this.deathCounter == 0 ? 1 : this.deathCounter));

        System.out.println("\n\n\n\n");
        System.out.println("======================================");
        System.out.println("               Goodbye!\n");
        System.out.println(" Your final statistics:\n");
        System.out.println(" Games played: " + totalGames + "\n");
        System.out.println(" Wins: " + this.winCounter + "\n");
        System.out.println(" Losses: " + this.lossCounter + "\n");
        System.out.printf(" Win %%: %.2f%%%n\n", winPercentage);
        System.out.println(" KDA: " + this.killCounter + "/" + this.deathCounter + "/" + this.assistCounter + "\n");
        System.out.printf(" KDR: %.2f%n\n", kdr);
        System.out.println("          See you next time!");
        System.out.println("======================================");
        System.out.println("\n\n\n\n");

    }

    // Print a win message and add to the win counter
    private void wonGame() {
        System.out.println("\nNice win!\n");

        this.winCounter++;
    }

    // Print a loss message and add to the loss counter
    private void lostGame() {
        System.out.println("\nOnto the next one\n");

        this.lossCounter++;
    }

    public static void main(String[] args) {
        GetWorkout gw = new GetWorkout(new Random());
        GetGame gg = new GetGame(new Scanner(System.in));

        GetWorkout.printWelcome();

        while (true) {
            try {
                Game game = gg.getGame();
                if (game.getWin()) {
                    gw.wonGame();
                } else {
                    gw.lostGame();
                }

                gw.killCounter += (Integer) game.getStatValue("kills");
                gw.deathCounter += (Integer) game.getStatValue("deaths");
                gw.assistCounter += (Integer) game.getStatValue("assists");

                ExerciseTodo exerciseTodo = gw.getExerciseTodo(game.calculateReps());
                exerciseTodo.complete(); // TODO (ephront): should set this up to be set with input from the user
                gw.workout.addExercise(exerciseTodo);
                System.out.println("\nHere's your workout:\n");
                System.out.println("\n" + exerciseTodo.getInstructionString());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (!gw.newGame()) {
                break;
            }
            System.out.println("\n\n");
        }

        gw.workout.complete();

        gw.printGoodbye();
    }
}
