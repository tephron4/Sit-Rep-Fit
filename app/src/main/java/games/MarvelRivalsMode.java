package games;

public enum MarvelRivalsMode {
    UNKNOWN_MODE("Unknown Game Mode");

    private final String displayString;

    private MarvelRivalsMode(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return this.displayString;
    }
}
