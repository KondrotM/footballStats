package uk.ac.glos.ct5025.s1804317.footballStats;

import uk.ac.glos.ct5025.s1804317.footballStats.UI.GameTimer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {
    // defines home and away team objects
    private Team homeTeam;
    private Team awayTeam;
    // game date based on date format
    private DateFormat dateFormat;
    private String gameDate;
    // tracks which team has possession of the ball
    private boolean possession;
    // increments time each second
    private GameTimer gt;
    // documents each entry in the game
    private Timeline timeLine;

    public Game (Team tempHomeTeam, Team tempAwayTeam){
        homeTeam = tempHomeTeam;
        awayTeam = tempAwayTeam;
        possession = true;
        gt = new GameTimer();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        // initialises a new timeline and passes this game as a parameter
        timeLine = new Timeline(this);

        // sets gameDate to be current date.
        // written as a string but ordered as Y/M/D H:M:S so that it can still be ordered
        gameDate = dateFormat.format(new Date());
    }

    public GameTimer getGameTimer(){
        return gt;
    }

    public Timeline getTimeLine(){
        return timeLine;
    }

    Team getHomeTeam(){ return homeTeam; }

    Team getAwayTeam(){
        return awayTeam;
    }

    String getGameDate(){
        return gameDate;
    }

    public boolean getPossession(){
        return possession;
    }

    public void changePossession(){
        // inverses which team has possession
        possession = !possession;
    }

    public void finishGame(){
        // compares win/lose/draw
        if (homeTeam.getGoals() > awayTeam.getGoals()){
            homeTeam.gameWon();
            awayTeam.gameLost();
        } else if (awayTeam.getGoals() > homeTeam.getGoals()) {
            awayTeam.gameWon();
            homeTeam.gameLost();
        } else {
            awayTeam.gameDrawn();
            homeTeam.gameDrawn();
        }
        // sets each team's goals for and goals against
        homeTeam.updateGoals(homeTeam.getGoals(),awayTeam.getGoals());
        awayTeam.updateGoals(awayTeam.getGoals(), homeTeam.getGoals());

        // wipes active players
        homeTeam.resetActivePlayers();
        awayTeam.resetActivePlayers();
    }

    public void initialiseGame(){
        // Flushes out local player data
        Player player;
        homeTeam.init();
        for (int i = 0; i < homeTeam.getActivePlayers().size(); i++){
            player = homeTeam.getActivePlayers().get(i);
            player.init();
        }
        awayTeam.init();
        for (int i = 0; i < awayTeam.getActivePlayers().size(); i++){
            player = awayTeam.getActivePlayers().get(i);
            player.init();
        }

    }



}
