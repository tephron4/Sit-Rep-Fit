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
import java.util.concurrent.ExecutorCompletionService;

import games.Game;
import main.java.workout_processing.GetGame;

public class GetWorkout {

    public class Workout {

        public enum WorkoutType {
            REPS,
            TIMED
        }

        private final String name;
        private final WorkoutType type;
        private int count = 0;

        public Workout(String name, WorkoutType type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return this.name;
        }

        public WorkoutType getType() {
            return this.type;
        }

        public String getTypeString() throws IllegalArgumentException {
            switch (this.type) {
                case REPS -> {
                    return "reps";
                }
                case TIMED -> {
                    return "secs";
                }
                default -> {
                    throw new IllegalArgumentException("Unknown workout type: " + this.type);
                }
            }
        }

        public int getCount() throws IllegalArgumentException {
            return switch (this.type) {
                case REPS ->
                    this.count;
                case TIMED ->
                    this.count * 5;
                default ->
                    throw new IllegalArgumentException("Unknown workout type: " + this.type);
            };
        }

        public void setCount(int c) throws IllegalArgumentException {
            if (c < 0) {
                throw new IllegalArgumentException("Count cannot be set to a negative number.");
            }

            this.count = c;
        }

        public void printWorkout() {
            System.out.print("Do " + this.getName() + " for " + this.getCount() + " " + this.getTypeString());
        }
    }

    private final Scanner scanner;
    private Random random;
    private List<Workout> workouts;

    public int winCounter;
    public int lossCounter;
    public int killCounter;
    public int deathCounter;
    public int assistCounter;

    public GetWorkout(Random random) {
        this.scanner = new Scanner(System.in);
        this.random = random;

        this.workouts = List.of(
                new Workout("push-ups", Workout.WorkoutType.REPS),
                new Workout("planks", Workout.WorkoutType.TIMED),
                new Workout("sit-ups", Workout.WorkoutType.REPS),
                new Workout("crunches", Workout.WorkoutType.REPS),
                new Workout("wall-sit", Workout.WorkoutType.TIMED),
                new Workout("lunges", Workout.WorkoutType.REPS),
                new Workout("squats", Workout.WorkoutType.REPS));

        this.winCounter = 0;
        this.lossCounter = 0;
        this.killCounter = 0;
        this.deathCounter = 0;
        this.assistCounter = 0;
    }

    public List<Workout> getWorkouts() {
        return this.workouts;
    }

    /**
     * Get the workout to do based on the given stats and game-outcome.
     *
     * @param reps number of reps
     * @return the workout to do, with an updated count
     */
    public Workout getWorkout(int reps) {
        // Get a random workout
        Workout todo = this.workouts.get(this.random.nextInt(this.workouts.size()));
        // Set the new count and return the workout
        todo.setCount(reps);
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
        System.out.println(" Win %: " + winPercentage + "%\n");
        System.out.println(" KDA: " + this.killCounter + "/" + this.deathCounter + "/" + this.assistCounter + "\n");
        System.out.println(" KDR: " + kdr + "\n");
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

                Workout workoutTodo = gw.getWorkout(game.calculateReps());
                System.out.println("\nHere's your workout:\n");
                workoutTodo.printWorkout();
                System.out.println("\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (!gw.newGame()) {
                break;
            }
        }

        gw.printGoodbye();
    }
}
