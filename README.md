# Sit-Rep-Fit
Stay active while gaming. This program is meant to help the user stay active while playing video games, with small workouts to do based on your in-game performance.

## Running

Run in a terminal with `java GetWorkout.java`.

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

## How it works <small>(last updated: Aug. 2, 2025)</small>

The program will ask you the user if they won or lost the game. Then it will ask for the number or kills, deaths, and assists that the user got in the game. After collecting the games outcome and the in-game stats from the user, the program will print the workout that the user should do.

### How the workout is determined

The workout is selected at random and the time (in seconds) or number of repititions is calculated based on the outcome of the game (win/loss) and the user's number of deaths.

If the user won the game, then the number of repititions of the workout to do will be equal to their number of deaths. If they lost, then the number will be doubled. 

#### NOTE: For time based workouts, the length of time is five times the number of repititions calculated.


# Future Plans:

* Update the time/repititions calculation logic
* Expand the program to be game based (Seperate/different statistics and seperate/different calculations)
* Database storage for historical workout data (for workout tracking)
* Connect to different game APIs for automatic game->workout processing (pulling game results/statistics from relative APIs)
* GUI (web based and/or application)
* API for other integrations
