# Sit-Rep-Fit
Stay active while gaming. This program is meant to help the user stay active while playing video games, with small workouts to do based on your in-game performance.

## Navigation
* [Workouts](#workouts)
  * [Supported Workouts](#supported-workouts)
* [Supported Games](#supported-games)
  * [Marvel Rivals](#marvel-rivals)
* [Building and Testing](#building-and-testing)
* [Running the CLI](#running-the-cli)
* [Future Plans](#future-plans)

## Workouts

The workouts are simple, not requiring any extra equipment, and are either time or repitition based.

### Supported workouts:
| workout | type |
| :--- | :--- |
| push-ups | reps |
| planks | time |
| sit-ups | reps |
| crunches | reps |
| wall-sit | time |
| lunges | reps |
| squats | reps |

## Supported Games

> NOTE: For time based workouts, the length of time is five times the number of repititions calculated.

### Marvel Rivals
For Marvel Rivals, the program supports the basic statistics: kills, deaths, and assists.

#### Rep Calculation
- For a win: $\text{reps}=\text{deaths}$
- For a loss: $\text{reps} = 2*\text{deaths}$

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

* Update the time/repititions calculation logic
* Expand the program to be game based (Seperate/different statistics and seperate/different calculations)
* Database storage for historical workout data (for workout tracking)
* Connect to different game APIs for automatic game->workout processing (pulling game results/statistics from relative APIs)
* GUI (web based and/or application)
* API for other integrations
