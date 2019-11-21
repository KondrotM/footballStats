package uk.ac.glos.ct5025.s1804317.footballStats;

public class Item {
    private int goalsScored;

    public Item(){
        goalsScored = 0;
    }

    public void scoreGoal() {
        goalsScored++;
    }

    public int getGoalsScored() { return goalsScored; }

    public void init(){
        goalsScored = 0;
    }
}