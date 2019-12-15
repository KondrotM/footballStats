package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;
import java.util.Date;

public class StaticGame {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String gameDate;
    private String gameTime;
    private ArrayList<String[]> homePlayerData;
    private ArrayList<String[]> awayPlayerData;
    private float possession;
    private ArrayList<Entry> timeline;


    public StaticGame(Game game){
        homeTeam = game.getHomeTeam().getName();
        awayTeam = game.getAwayTeam().getName();
        homeTeamGoals = game.getHomeTeam().getGoals();
        awayTeamGoals = game.getHomeTeam().getGoals();
        gameDate = game.getGameDate();
        gameTime = game.getGameTimer().getWatchTime();
        homePlayerData = new ArrayList<String[]>();
        awayPlayerData = new ArrayList<String[]>();
        for (Player player : game.getHomeTeam().getActivePlayers()){
            String[] playerTuple = new String[]{player.getName(),Integer.toString(player.getGoals())};
            homePlayerData.add(playerTuple);
        }
        for (Player player : game.getAwayTeam().getActivePlayers()){
            String[] playerTuple = new String[]{player.getName(),Integer.toString(player.getGoals())};
            awayPlayerData.add(playerTuple);
        }
        possession = game.getTimeLine().getPossession();
//        timeline = game.getTimeLine().getTimeline();
    }

    public StaticGame(String tempHomeTeam, String tempAwayTeam, int tempHomeTeamGoals, int tempAwayTeamGoals, String tempGameDate, String tempGameTime, ArrayList<String[]> tempHomePlayerData, ArrayList<String[]> tempAwayPlayerData, float tempPossession){
        homeTeam = tempHomeTeam;
        awayTeam = tempAwayTeam;
        homeTeamGoals = tempHomeTeamGoals;
        awayTeamGoals = tempAwayTeamGoals;
        gameDate = tempGameDate;
        gameTime = tempGameTime;
        homePlayerData = tempHomePlayerData;
        awayPlayerData =  tempAwayPlayerData;
        possession = tempPossession;
    }



    public float getPossession(){
        return possession;
    }

    public String getTitle(){
        return gameDate + " - " + homeTeam + " vs " + awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public String getGameDate() {
        return gameDate;
    }

    public String getGameTime() {
        return gameTime;
    }

    public ArrayList<String[]> getHomePlayerData() {
        return homePlayerData;
    }

    public ArrayList<String[]> getAwayPlayerData() {
        return awayPlayerData;
    }
}
