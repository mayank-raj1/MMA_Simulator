package mma_simulator;
public class Tournament {
	private MatchManager matchManager;
	private FighterManager fighterManager;
	private double prize;
	String tournamentPrefix;

	public Tournament(double prize, String tournamentPrefix) {
		this.prize = prize;
		this.tournamentPrefix = tournamentPrefix;
		this.fighterManager = new FighterManager();
		this.matchManager = new MatchManager();
	}

	public Fighter[] getFighters() {
		return fighterManager.getAllFighters();
	}

	public Fighter getFighter(String fighterId){
		return this.fighterManager.getFighter(fighterId);
	}

	public void addFighter(String name, double strength, double speed, double skill, int age, double weight) {
		try {
			this.fighterManager.addFighter(new Fighter(name, strength, speed, skill, age, weight));
			System.out.println("Fighter added!");
		}
		catch (Exception e){
			System.out.println("Fighter cannot be added\n"+e.getMessage());
		}
	}

	public Match getMatch(String matchId){
		return this.matchManager.getMatch(matchId);
	}

	public void start(){
		matchManager.createTournamentTree(fighterManager, this.tournamentPrefix);
		matchManager.playMatches();
	}
}