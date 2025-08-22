/**
 * The GetGame class is used for getting the game
 * with it's result and statistics, through the
 * command line.
 * 
 * @author Tobias Ephron
 */

package workout_processing;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import games.Game;
import games.MarvelRivals;

public class GetGame {

    private final Scanner sc;

    public GetGame(Scanner scanner) {
        this.sc = scanner;
    }

    /**
     * Gets a game with results and stats from the user.
     * 
     * @return a game with results and stats
     */
    public Game getGame() {
        Class<? extends Game> type = getGameType();

        return this.getGameResults(type);
    }

    private Class<? extends Game> getGameType() {
        Class<? extends Game> chosenClass = null;

        System.out.println("What game did you play?");

        while (chosenClass == null) {
            System.out.println("--------------------------------");
            System.out.println("| Select one of the following: |");
            System.out.println("--------------------------------");
            System.out.println("| 1............. Marvel Rivals |");
            System.out.println("|                              |");
            System.out.println("|      (more coming soon)      |");
            System.out.println("--------------------------------");
            System.out.println("");

            int resp = 0;

            try {
                resp = Integer.parseInt(this.sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n\nPlease input a valid integer from the list of games\n\n");
                continue;
            }

            switch (resp) {
                case 1:
                    chosenClass = MarvelRivals.class;
                    continue;
                default:
                    System.out.println("\n\nPlease select an option from the given list\n\n");
                    continue;
            }
        }

        return chosenClass;
    }

    private Game getGameResults(Class<? extends Game> game) {
        Game tempGameInstance = null;

        try {
            tempGameInstance = game.getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }

        List<String> statNames = tempGameInstance.getStatNames();
        Map<String, Class> stats = tempGameInstance.getStats();

        boolean win = this.getWin();

        Map<String, Object> statValues = new HashMap<>();
        for (int i = 0; i < statNames.size(); i++) {
            statValues.put(statNames.get(i), this.getStatValue(statNames.get(i), stats.get(statNames.get(i))));
        }

        try {
            Game g = game.getConstructor(Timestamp.class, Map.class, boolean.class)
                    .newInstance(Timestamp.from(Instant.now()), statValues, win);
            return g;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean getWin() {
        while (true) {
            System.out.println("\n\nDid you win the game? (Y/n)");
            String resp = this.sc.nextLine().strip();

            if (resp.isEmpty()) {
                System.out.println("\nPlease enter Yes/no (Y/n)");
                continue;
            }

            switch (resp.toUpperCase().charAt(0)) {
                case 'Y':
                    return true;
                case 'N':
                    return false;
                default:
                    System.out.println("\nPlease answer with Yes/no (Y/n)");
            }
        }
    }

    private Object getStatValue(String stat, Class type) {
        if (type == Integer.class) {
            return this.getIntegerStat(stat);
        } else {
            // TODO (ephront): Add support for other Classes (Float, Boolean, etc.)
            throw new IllegalArgumentException("Unsupported stat type: " + type.getName());
        }
    }

    private Integer getIntegerStat(String stat) {
        while (true) {
            System.out.println("\n\nWhat did you get for " + stat + "?");
            String resp = this.sc.nextLine().strip();

            try {
                return Integer.parseInt(resp);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("\nPlease enter an integer value");
                continue;
            }
        }
    }
}
