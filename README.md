# Sit-Rep-Fit

Stay active while gaming. This program is meant to help the user stay active while playing video games, with small exercises to do based on your in-game performance.

## Navigation

- [Exercises](#exercises)
  - [Supported Exercises](#supported-exercises)
- [Supported Games](#supported-games)
  - [Marvel Rivals](#marvel-rivals)
  - [Valorant](#valorant)
- [Building and Testing](#building-and-testing)
- [Running the CLI](#running-the-cli)
- [Future Plans](#future-plans)

## Exercises

The exercises are simple, not requiring any extra equipment, and are either repitition or time based.

### Supported Exercises:

| exercise | type |
| :------- | :--- |
| push-ups | reps |
| planks   | time |
| sit-ups  | reps |
| crunches | reps |
| wall-sit | time |
| lunges   | reps |
| squats   | reps |

## Supported Games

> NOTE: For time based exercises, the length of time is five times the number of repititions calculated.

### Marvel Rivals

For Marvel Rivals, the program supports the basic statistics: kills, deaths, and assists.

#### Rep Calculation

- For a win: $\text{reps}=\text{deaths}$
- For a loss: $\text{reps} = 2*\text{deaths}$

### Valorant

For Valorant, the program supports specifying game mode played and the basic statistics: kills, deaths, and assists.

#### Rep Calculation

This will vary based on game mode played, but will be based generally around whether it was a win or loss and deaths.

## Building and Testing

This program is built using Gradle and can be built with:

```
./gradlew clean build
```

> add `-x` flag to the end to not run tests when building

Tests can be run using:

```
./gradlew test
```

> you can specify the test(s) to run with `--tests <path/to/test(s)>`

## Running the CLI

The program can be run directly in the terminal with:

```
./gradlew --console plain run
```

# Future Plans:

- Add more supported games
- Update the time/repititions calculation logic
- Database storage for historical exercise/workout data (for tracking)
- Connect to different game APIs for automatic game->exercise processing (pulling game results/statistics from relative APIs)
- GUI (web based and/or application)
- API for other integrations
