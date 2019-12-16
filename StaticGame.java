package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

// StaticGame is a static instance of a game, which can be used for storing local games.
public class StaticGame {
    // names of teams
    private String homeTeam;
    private String awayTeam;
    // team goals
    private int homeTeamGoals;
    private int awayTeamGoals;
    // date of game and how long it lasted
    private String gameDate;
    private String gameTime;
    // players and their respective goals scored
    private ArrayList<String[]> homePlayerData;
    private ArrayList<String[]> awayPlayerData;
    // percentage of home team possession
    private float possession;
    // timeline of entries
    private ArrayList<Entry> timeline;
    // team objects for playback
    private Team homeTeamObject;
    private Team awayTeamObject;

    // loads static info from game object
    public StaticGame(Game game){
        homeTeamObject = game.getHomeTeam();
        awayTeamObject = game.getAwayTeam();

        homeTeam = game.getHomeTeam().getName();
        awayTeam = game.getAwayTeam().getName();
        homeTeamGoals = game.getHomeTeam().getGoals();
        awayTeamGoals = game.getHomeTeam().getGoals();
        gameDate = game.getGameDate();
        gameTime = game.getGameTimer().getWatchTime();
        possession = game.getTimeLine().getPossession();
        timeline = game.getTimeLine().getTimeline();
        // initialises new arraylists and appends player data
        // each player's data is a String list, imitating a tuple
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

    }


    // loads static info from XML
    public StaticGame(String tempHomeTeam, String tempAwayTeam, int tempHomeTeamGoals, int tempAwayTeamGoals,
                      String tempGameDate, String tempGameTime, ArrayList<String[]> tempHomePlayerData,
                      ArrayList<String[]> tempAwayPlayerData, float tempPossession, int tempHomeTeamIndex,
                      int tempAwayTeamIndex, ArrayList<Entry> tempTimeline){
        homeTeam = tempHomeTeam;
        awayTeam = tempAwayTeam;
        // gets team object from team list index
        homeTeamObject = Tournament.getActiveTournament().getTournamentTeams().get(tempHomeTeamIndex);
        awayTeamObject = Tournament.getActiveTournament().getTournamentTeams().get(tempAwayTeamIndex);
        homeTeamGoals = tempHomeTeamGoals;
        awayTeamGoals = tempAwayTeamGoals;
        gameDate = tempGameDate;
        gameTime = tempGameTime;
        homePlayerData = tempHomePlayerData;
        awayPlayerData =  tempAwayPlayerData;
        possession = tempPossession;
        timeline = tempTimeline;
    }

    // getters

    public ArrayList<Entry> getTimeline(){
        return timeline;
    }

    public Team getHomeTeamObject(){
        return homeTeamObject;
    }

    public Team getAwayTeamObject(){
        return awayTeamObject;
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
