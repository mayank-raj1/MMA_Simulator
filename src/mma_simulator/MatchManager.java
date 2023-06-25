package mma_simulator;

import java.util.HashMap;
import java.util.Scanner;
public class MatchManager {
    private final HashMap<String, Match> matches;
    private final Node finalMatch;
    private int levels;
    private String tournamentPrefix;
    private final Scanner input;
    protected MatchManager(){
        this.matches = new HashMap<String, Match>();
        this.finalMatch = null;
        levels = 0;
        input = new Scanner(System.in);
    }

    protected Match getMatch(String matchId){
        return matches.get(matchId);
    }
    protected Match getFinalMatch(){
        return finalMatch.match;
    }
    protected void createTournamentTree(FighterManager fighterManager, String prefix){
        this.levels = (int) Math.ceil(Math.sqrt(fighterManager.getNumOfFighters()));

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

    public void playMatches(){
        playLevel(this.finalMatch);
    }

    private void playLevel(Node round) {
        if (round == null) {
            return;
        }
        if (round.left == null && round.right == null) {
            round.match.fight();
            System.out.println(round.match.getResults());
            round.addFighter(round.match.getWinner());
            System.out.println("Press Enter to continue...");
            this.input.nextLine();
        } else {
            playLevel(round.left);
            playLevel(round.right);
        }
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
            if (this.parent==null){}
            else if (this.parent.left == this){
                this.parent.match.setFighter1(fighter);
            }
            else {
                this.parent.match.setFighter2(fighter);
            }
        }
    }
}