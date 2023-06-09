import java.util.HashMap;
import java.util.List;

public class Tournament {

	private HashMap<String, Fighter> fighters;
	private HashMap<String, Match> matches;


	public Fighter[] getFighters() {
		return this.fighters.values().toArray(new Fighter[0]);
	}
	public Fighter getFighter(String fighterId){
		return fighters.get(fighterId);
	}
	public void addFighter(Fighter fighters) {
		this.fighters.put(fighters.getId(), fighters);
	}

	public Match[] getMatches() {
		return this.matches.values().toArray(new Match[0]);
	}

	public Match getMatch(String matchId){
		return matches.get(matchId);
	}
	public void addMatch(Match match){
		this.matches.put(match.getMatchId(), match);
	}
	public void start(){

	}
}