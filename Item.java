package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

// Item is used to extend team and player classes
public abstract class Item {
    private String name;

    // item stats
    private int goalsFor;
    private int gamesWon;
    private int gamesDrawn;
    private int gamesLost;
    // goals refers to goals local to a game while goalsFor carries over for the whole tournament
    private int goals;

    /**
     * Item created for appending goals to
     * @param tempName
     */

    public Item(String tempName) {
        name = tempName;
        goals = 0;
        gamesWon = 0;
        gamesDrawn = 0;
        gamesLost = 0;
    }

    /**
     * Gets games played
     * @return games played
     */

    public void incrementGoalsFor(){
        goalsFor++;
    }

    public void incrementGamesWon(){
        gamesWon++;
    }

    public void incrementGamesLost(){
        gamesWon++;
    }

    public void incrementGamesDrawn(){
        gamesWon++;
    }

    public void incrementGoals(){
        goals++;
    }

    public int getGamesPlayed(){
        // sums all games played
        int gamesPlayed = gamesWon + gamesDrawn + gamesLost;
        return gamesPlayed;
    }


    // setters
    public void setGoals(int goals) {
        this.goals = goals;
    }

    // package-private as it is only used by classes which inherit this

    void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    void setGamesDrawn(int gamesDrawn) {
        this.gamesDrawn = gamesDrawn;
    }

    void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    // getters

    public String getName() { return name; }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoals() { return goals; }

    public int getGamesWon(){
        return gamesWon;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    // increments variables
    public void gameWon() {
        gamesWon++;
    }

    public void gameDrawn(){
        gamesDrawn++;
    }

    public void gameLost(){
        gamesLost++;
    }

    public void scoreGoal() {
        goals++;
    }

    // clears local goals
    void init(){
        goals = 0;
    }

}