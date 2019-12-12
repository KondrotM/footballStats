package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Element {
    protected int goals;
    private String name;

    // protected so as they can be used with children classes
    protected int goalsFor;
    protected int gamesWon;
    protected int gamesDrawn;
    protected int gamesLost;


    public Element(String tempName) {
        name = tempName;
        goals = 0;


        gamesWon = 0;
        gamesDrawn = 0;
        gamesLost = 0;
    }


    public int getGamesPlayed(){
        int gamesPlayed = gamesWon + gamesDrawn + gamesLost;
        return gamesPlayed;
    }

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
        goalsFor++;
    }

    public void init(){
        goals = 0;
    }

    public Element getElement(ArrayList elementList){
        System.out.println(".. Back");
        Element currElement;
        for (int i = 0; i < elementList.size(); i++) {
            currElement = (Element) elementList.get(i);
            System.out.println(i+1 + ". " + currElement.getName());
        }
        String choice = main.getInput(null);
        if (choice.equals("..")){
            return null;
        } else {
            int elementNo = Integer.parseInt(choice);
            currElement = (Element) elementList.get(elementNo-1);
            return currElement;
        }
    }
}