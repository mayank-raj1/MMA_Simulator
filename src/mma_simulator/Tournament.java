package mma_simulator;

public class Tournament {
	private final MatchManager matchManager;
	private final FighterManager fighterManager;
	private final double prize;
	private final String tournamentPrefix;

	/**
	 * Constructs a Tournament object.
	 *
	 * @param prize           the prize amount for the tournament
	 * @param tournamentPrefix the prefix to be used for generating match IDs
	 */
	public Tournament(double prize, String tournamentPrefix) {
		this.prize = prize;
		this.tournamentPrefix = tournamentPrefix;
		this.fighterManager = new FighterManager();
		this.matchManager = new MatchManager();
	}

	/**
	 * Retrieves an array of all fighters participating in the tournament.
	 *
	 * @return an array of fighters
	 */
	public Fighter[] getFighters() {
		return fighterManager.getAllFighters();
	}

	/**
	 * Retrieves a specific fighter by their ID.
	 *
	 * @param fighterId the ID of the fighter
	 * @return the fighter with the specified ID, or null if not found
	 */
	public Fighter getFighter(String fighterId){
		return this.fighterManager.getFighter(fighterId);
	}

	/**
	 * Adds a fighter to the tournament.
	 *
	 * @param name   the name of the fighter
	 * @param strength the strength of the fighter
	 * @param speed    the speed of the fighter
	 * @param skill    the skill of the fighter
	 * @param age      the age of the fighter
	 * @param weight   the weight of the fighter
	 */
	public void addFighter(String name, double strength, double speed, double skill, int age, double weight) {
		try {
			this.fighterManager.addFighter(new Fighter(name, strength, speed, skill, age, weight));
			System.out.println("Fighter added!");
		} catch (Exception e) {
			System.out.println("Fighter cannot be added\n" + e.getMessage());
		}
	}

	/**
	 * Retrieves a specific match by its ID.
	 *
	 * @param matchId the ID of the match
	 * @return the match with the specified ID, or null if not found
	 */
	public Match getMatch(String matchId){
		return this.matchManager.getMatch(matchId);
	}

	/**
	 * Starts the tournament by creating the tournament tree, playing matches, and awarding the prize to the winner.
	 */
	public void start(){
		matchManager.createTournamentTree(fighterManager, this.tournamentPrefix);
		matchManager.playMatches();
		matchManager.getFinalMatch().getWinner().addMoney(this.prize);
		System.out.println(matchManager.getFinalMatch().getWinner().getName()+" Won the tournament!!");
	}
}
