package uk.ac.glos.ct5025.s1804317.footballStats;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class Game {
    private String gameTitle;
    private Team homeTeam;
    private Team awayTeam;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private String gameDate;

    private boolean gameFinished = false;

    private ArrayList homePlayers;
    private ArrayList awayPlayers;

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




    public Game (Team tempHomeTeam, Team tempAwayTeam, ArrayList tempHomePlayers, ArrayList tempAwayPlayers){
        homeTeam = tempHomeTeam;
        awayTeam = tempAwayTeam;
        homePlayers = tempHomePlayers;
        awayPlayers = tempAwayPlayers;
    }

    public Team getHomeTeam(){ return homeTeam; }

    public Team getAwayTeam(){
        return awayTeam;
    }

    public String getGameDate(){
        return gameDate;
    }

    public String getGameTitle(){ return gameTitle; }

    public ArrayList getAwayPlayers() {
        return awayPlayers;
    }

    public ArrayList getHomePlayers() {
        return homePlayers;
    }

    public Game startGame() {
        // sets gameDate to be current date.
        // written as a string but ordered as Y/M/D H:M:S so that it can still be ordered
        Date date = new Date();
        gameDate = dateFormat.format(date);

        // sets title which will be displayed after the game is over
        gameTitle = homeTeam.getTeamName() + " vs " + awayTeam.getTeamName() + " --- " + gameDate;

        System.out.println(homePlayers);


        while (!gameFinished){
            getGameMenu();
        }
        Game gameResult =  this;
        return gameResult;
    }

}
