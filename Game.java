package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Game {
    private Team homeTeam;
    private Team awayTeam;
    private ArrayList homePlayers;
    private ArrayList awayPlayers;
    private String gameDate;
    private boolean gameFinished = false;

    private void getGameMenu() {
        System.out.println(homeTeam.getTeamName() + " - "
                + homeTeam.getGoalsScored() + " -- "
                + awayTeam.getGoalsScored() + " - "
                + awayTeam.getTeamName());
        System.out.println("1. Score Goal");
        System.out.println("2. End Game");
        String choice = main.getInput(null);
        if (choice.equals("1")){
            System.out.println(".. Back");
            System.out.println("1. Home Team");
            System.out.println("2. Away Team");
            choice = main.getInput(null);
            if (choice.equals("1")){ homeTeam.scoreGoal(); }
            if (choice.equals("2")){ awayTeam.scoreGoal(); }
        } else if (choice.equals("2")){
            gameFinished = true;
        }

    }



    public Game (Team tempHomeTeam, Team tempAwayTeam){
        homeTeam = tempHomeTeam;
        awayTeam = tempAwayTeam;
    }

    public Team getHomeTeam(){ return homeTeam; }

    public Team getAwayTeam(){
        return awayTeam;
    }

    public String getGameDate(){
        return gameDate;
    }



    public void startGame() {
        while (!gameFinished){
            getGameMenu();
        }
    }

}
