package games;

public enum ValorantGameMode {
    UNRATED("Unrated"),
    COMPETITIVE("Competitive"),
    SWIFTPLAY("Swiftplay"),
    SPIKE_RUSH("Spike Rush"),
    DEATHMATCH("Deathmatch"),
    ESCALATION("Escalation"),
    TEAM_DEATHMATCH("Team Deathmatch"),
    UNKNOWN_MODE("Unknown Game Mode");

    private final String displayString;

    private ValorantGameMode(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return this.displayString;
    }

}