package mma_simulator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class FighterManager {
    private final HashMap<String, Fighter> fighters;
    private final Queue<Fighter> fighterQueue;

    protected FighterManager() {
        fighters = new HashMap<String, Fighter>();
        fighterQueue = new LinkedList<Fighter>();
    }

    protected void addFighter(Fighter fighter) {
        fighters.put(fighter.getId(), fighter);
        fighterQueue.add(fighter);
    }

    protected Fighter[] getAllFighters() {
        return fighters.values().toArray(new Fighter[0]);
    }

    protected Fighter getFighter(String fighterId){
        return this.fighters.get(fighterId);
    }

    protected Fighter nextFighter(){
        return this.fighterQueue.poll();
    }

    protected int getNumOfFighters(){
        return this.fighters.size();
    }
}
