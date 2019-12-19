package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Player extends Item {

    private String playerDoB;
    // shows which team player belongs to
    private Team playerTeam;

    //Creates a Player object which can then be used to assign new players
    public Player(String name, String tempDoB, Team team){
        super(name);
        playerDoB = tempDoB;
        playerTeam = team;
    }

    public void addToTeam(){
        playerTeam.addPlayer(this);
    }

    public String getPlayerDoB(){
        return playerDoB;
    }

    // Overrides item method to also score for the player's team
    @Override
    public void scoreGoal(){
        playerTeam.scoreGoal();
        incrementGoals();
        incrementGoalsFor();
    }

    }
