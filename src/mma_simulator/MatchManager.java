package mma_simulator;

import java.util.HashMap;
import java.util.Scanner;

public class MatchManager {
    private final HashMap<String, Match> matches;
    private Node finalMatch;
    private int levels;
    private String tournamentPrefix;
    private final Scanner input;

    protected MatchManager(){
        this.matches = new HashMap<String, Match>();
        this.finalMatch = null;
        levels = 0;
        input = new Scanner(System.in);
    }

    /**
     * Retrieves a specific match by its ID.
     *
     * @param matchId the ID of the match
     * @return the match with the specified ID, or null if not found
     */
    protected Match getMatch(String matchId){
        return matches.get(matchId);
    }

    /**
     * Retrieves the final match of the tournament.
     *
     * @return the final match
     */
    protected Match getFinalMatch(){
        return finalMatch.match;
    }

    /**
     * Creates the tournament tree based on the number of fighters and the given prefix.
     *
     * @param fighterManager the FighterManager object containing the fighters
     * @param prefix         the prefix to be used for generating match IDs
     */
    protected void createTournamentTree(FighterManager fighterManager, String prefix){
        this.levels = (int) Math.ceil(Math.log(fighterManager.getNumOfFighters()) / Math.log(2));
        this.tournamentPrefix = prefix;
        fighterManager.makeFighterQueue();
        this.finalMatch = createLevel(levels, null, fighterManager);
    }

    private Node createLevel(int level, Node parentNode, FighterManager fighterManager){
        if (level == 1){
            Match temp = new Match(fighterManager.nextFighter(), fighterManager.nextFighter(), this.tournamentPrefix);
            matches.put(temp.getMatchId(), temp);
            return new Node(temp, parentNode);
        }
        Match tempMatch = new Match(this.tournamentPrefix);
        matches.put(tempMatch.getMatchId(), tempMatch);
        Node tempNode = new Node(tempMatch, parentNode);
        tempNode.left = createLevel(level-1, tempNode, fighterManager);
        tempNode.right = createLevel(level-1, tempNode, fighterManager);
        return tempNode;
    }

    /**
     * Plays all the matches in the tournament tree.
     */
    public void playMatches(){
        playLevel(this.finalMatch);
    }

    private void playLevel(Node round) {
        if (round == null) {
            return;
        }

        playLevel(round.left);
        playLevel(round.right);

        round.match.fight();
        System.out.println(round.match.getResults());
        round.addFighter(round.match.getWinner());
        System.out.println("Press Enter to continue...");
        this.input.nextLine();

    }

    private static class Node {
        Match match;
        Node left;
        Node right;
        Node parent;

        Node(Match match, Node parentNode ) {
            this.match = match;
            this.left = null;
            this.right = null;
            this.parent = parentNode;
        }

        void addFighter(Fighter fighter){
            if (this.parent == null){
                // No parent node, do nothing
            } else if (this.parent.left == this){
                this.parent.match.setFighter1(fighter);
            } else {
                this.parent.match.setFighter2(fighter);
            }
        }
    }
}
