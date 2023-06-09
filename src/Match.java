public class Match {
    private static int baseMatchId = 1000;
    private final String matchId;
    private Fighter fighter1;
    private Fighter fighter2;
    private Record record;

    public Match(Fighter fighter1, Fighter fighter2) {
        this.setFighter1(fighter1);
        this.setFighter2(fighter2);
        matchId = Integer.toString(baseMatchId++);
    }

    public Match(Fighter fighter1, Fighter fighter2, int matchId) {
        this.setFighter1(fighter1);
        this.setFighter2(fighter2);
        this.matchId = Integer.toString(matchId);
    }

    public String getMatchId() {
        return this.matchId;
    }

    public Fighter getFighter1() {
        return this.fighter1;
    }

    public void setFighter1(Fighter fighter1) throws IllegalArgumentException {
        if (!(fighter1.getStatus() == Fighter.Status.Available)) {
            throw new IllegalArgumentException("The Fighter should be available");
        }
        this.fighter1 = fighter1;
        fighter1.inMatch();
    }

    public Fighter getFighter2() {
        return this.fighter2;
    }

    public void setFighter2(Fighter fighter2) {
        if (!(fighter2.getStatus() == Fighter.Status.Available)) {
            throw new IllegalArgumentException("The Fighter should be available");
        }
        this.fighter2 = fighter2;
        fighter2.inMatch();
    }

    public void fight() {
        int[] score = {0, 0};

        if (fighter1.getSkill() > fighter2.getSkill()) {
            score[0]++;
        } else if (fighter1.getSkill() == fighter2.getSkill()) {
            score[0]++;
            score[1]++;
        } else {
            score[1]++;
        }
        if (fighter1.getStrength() > fighter2.getStrength()) {
            score[0]++;
        } else if (fighter1.getStrength() == fighter2.getStrength()) {
            score[0]++;
            score[1]++;
        } else {
            score[1]++;
        }
        if (fighter1.getSpeed() > fighter2.getSpeed()) {
            score[0]++;
        } else if (fighter1.getSpeed() == fighter2.getSpeed()) {
            score[0]++;
            score[1]++;
        } else {
            score[1]++;
        }
        this.record = new Record(this, (score[0] > score[1] ? fighter1 : fighter2), score);
        fighter1.addRecord(record);
        fighter2.addRecord(record);
    }

    public String getResults() {
        return String.format("%S won, score[ %d : %d] ", this.record.winner().getName(), this.record.score()[0], this.record.score()[1]);
    }
}