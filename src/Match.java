import org.jetbrains.annotations.NotNull;

/**
 * Represents a match between two fighters.
 */
public class Match {
    private static int baseMatchId = 1000;
    private final String matchId;
    private Fighter fighter1;
    private Fighter fighter2;
    private Record record;

    /**
     * Constructs a Match object with the provided fighters and a prefix for generating a unique match ID.
     *
     * @param fighter1 The first fighter.
     * @param fighter2 The second fighter.
     * @param prefix   The prefix for generating a unique match ID.
     */
    public Match(Fighter fighter1, Fighter fighter2, String prefix) {
        this.setFighter1(fighter1);
        this.setFighter2(fighter2);
        matchId = prefix + (baseMatchId++); // Generates a unique match ID by appending the prefix to baseMatchId
    }

    /**
     * Constructs a Match object with the provided fighters and a match ID.
     *
     * @param fighter1 The first fighter.
     * @param fighter2 The second fighter.
     * @param matchId  The match ID.
     */
    public Match(String matchId, Fighter fighter1, Fighter fighter2) {
        this.setFighter1(fighter1);
        this.setFighter2(fighter2);
        this.matchId = matchId; // Sets the match ID using the provided integer value
    }

    /**
     * Constructs a Match object with a prefix for generating a unique match ID.
     *
     * @param prefix   The prefix for generating a unique match ID.
     */
    public Match(String prefix) {
        this.matchId = prefix + baseMatchId++; // Sets the match ID using the provided integer value
    }

    /**
     * Returns the match ID.
     *
     * @return The match ID.
     */
    public String getMatchId() {
        return matchId;
    }

    /**
     * Returns the first fighter.
     *
     * @return The first fighter.
     */
    public Fighter getFighter1() {
        return fighter1;
    }

    /**
     * Sets the first fighter.
     *
     * @param fighter1 The first fighter to set.
     */
    public void setFighter1(Fighter fighter1) {
        validateAvailability(fighter1); // Validates fighter availability
        this.fighter1 = fighter1;
        fighter1.inMatch(); // Sets the first fighter and updates their status
    }

    /**
     * Returns the second fighter.
     *
     * @return The second fighter.
     */
    public Fighter getFighter2() {
        return fighter2;
    }

    /**
     * Sets the second fighter.
     *
     * @param fighter2 The second fighter to set.
     */
    public void setFighter2(Fighter fighter2) {
        validateAvailability(fighter2); // Validates fighter availability
        this.fighter2 = fighter2;
        fighter2.inMatch(); // Sets the second fighter and updates their status
    }

    /**
     * Performs the fight between the two fighters, calculates the scores, and records the match result.
     */
    public void fight() {
        int[] score = calculateScores(); // Calculates the scores for each fighter

        this.record = new Record(this, (score[0] > score[1] ? fighter1 : fighter2), score); // Creates a new record for the match with the winner and scores

        fighter1.addRecord(record); // Adds the record to the fighter's list of records
        fighter2.addRecord(record); // Adds the record to the fighter's list of records
    }

    /**
     * Returns the results of the match including the winner's name and scores.
     *
     * @return The match results.
     */
    public String getResults() {
        return String.format("%S won, score[ %d : %d] ", record.winner().getName(), record.score()[0], record.score()[1]);
    }

    //private util methods
    private void validateAvailability(@NotNull Fighter fighter) {
        if (fighter.getStatus() != Fighter.Status.Available) {
            throw new IllegalArgumentException("The Fighter should be available"); // Throws an exception if the fighter is not available
        }
    }

    private int[] calculateScores() {
        int[] score = new int[2];

        score[0] += compareSkills(fighter1, fighter2);
        score[1] += compareSkills(fighter2, fighter1);

        score[0] += compareStrengths(fighter1, fighter2);
        score[1] += compareStrengths(fighter2, fighter1);

        score[0] += compareSpeeds(fighter1, fighter2);
        score[1] += compareSpeeds(fighter2, fighter1);

        return score;
    }

    private double compareSkills(@NotNull Fighter fighter1, @NotNull Fighter fighter2) {
        return Double.compare(fighter1.getSkill(), fighter2.getSkill());
    }

    private double compareStrengths(@NotNull Fighter fighter1, @NotNull Fighter fighter2) {
        return Double.compare(fighter1.getStrength(), fighter2.getStrength());
    }

    private double compareSpeeds(@NotNull Fighter fighter1, @NotNull Fighter fighter2) {
        return Double.compare(fighter1.getSpeed(), fighter2.getSpeed());
    }
}
