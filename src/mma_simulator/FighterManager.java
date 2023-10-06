package mma_simulator;

import java.util.*;

public class FighterManager {
    private final HashMap<String, Fighter> fighters;
    private final Queue<Fighter> fighterQueue;

    /**
     * Constructs a FighterManager object.
     * Initializes the internal data structures for managing fighters.
     */
    protected FighterManager() {
        fighters = new HashMap<String, Fighter>();
        fighterQueue = new LinkedList<Fighter>();
    }

    /**
     * Adds a fighter to the fighter manager.
     *
     * @param fighter the fighter to add
     */
    protected void addFighter(Fighter fighter) {
        fighters.put(fighter.getId(), fighter);
    }

    /**
     * Retrieves an array of all fighters managed by the fighter manager.
     *
     * @return an array of fighters
     */
    protected Fighter[] getAllFighters() {
        return fighters.values().toArray(new Fighter[0]);
    }

    /**
     * Retrieves a specific fighter by their ID.
     *
     * @param fighterId the ID of the fighter
     * @return the fighter with the specified ID, or null if not found
     */
    protected Fighter getFighter(String fighterId){
        return this.fighters.get(fighterId);
    }

    /**
     * Retrieves the next fighter from the fighter queue.
     *
     * @return the next fighter in the queue, or null if the queue is empty
     */
    protected Fighter nextFighter(){
        return this.fighterQueue.poll();
    }

    /**
     * Retrieves the number of fighters managed by the fighter manager.
     *
     * @return the number of fighters
     */
    protected int getNumOfFighters(){
        return this.fighters.size();
    }

    protected void makeFighterQueue(){
        ArrayList<Fighter> tempArr = new ArrayList<Fighter>(fighters.values());
        while (tempArr.size()!=0){
            fighterQueue.add(tempArr.remove((int)Math.floor(Math.random()*tempArr.size())));
        }
    }

    protected void addAllFighters(Map<String, Fighter> fighterMap){
        this.fighters.putAll(fighterMap);
    }
}
