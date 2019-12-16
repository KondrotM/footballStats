package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

// Item is used to extend team and player classes
public class Item {
    private String name;

    // protected so as they can be used with children classes
    protected int goalsFor;
    protected int gamesWon;
    protected int gamesDrawn;
    protected int gamesLost;
    // goals refers to goals local to a game while goalsFor carries over for the whole tournament
    protected int goals;


    public Item(String tempName) {
        name = tempName;
        goals = 0;
        gamesWon = 0;
        gamesDrawn = 0;
        gamesLost = 0;
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
    public void init(){
        goals = 0;
    }

}