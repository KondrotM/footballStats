//Add playerID so that there aren't duplicates

package uk.ac.glos.ct5025.s1804317.footballStats;

import uk.ac.glos.ct5025.s1804317.footballStats.UI.Create.CreateTeamWindow;

import javax.swing.*;
import java.util.ArrayList;

public class Team extends Item {
    // players on the team
    private ArrayList<Player> teamPlayers;
    // players in a specified game
    private ArrayList<Player> activePlayers;

    // Holds all players to show within UI
    private DefaultListModel teamPlayersModel;
    private DefaultListModel teamActivePlayersModel;

    // goals against and points are stored separate from Item, as they are team-specific and not player
    private int goalsAgainst;
    private int points;


    public Team(String tempName) {
        // sends name to Item constructor
        super(tempName);

        // initialises list model
        teamPlayersModel = new DefaultListModel();
        teamActivePlayersModel = new DefaultListModel();

        //declares an Array to store the team's players
        teamPlayers = new ArrayList();
        // declares an Array to store active players for future games
        activePlayers = new ArrayList();

        setGoalsFor(0);
        goalsAgainst = 0;
        points = 0;
    }

    public DefaultListModel getTeamPlayersModel() {
        return teamPlayersModel;
    }

    public DefaultListModel getTeamActivePlayersModel() {
        return teamActivePlayersModel;
    }

    // getters

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalsDifference() {
        int goalsDifference = getGoalsFor() - goalsAgainst;
        return goalsDifference;
    }

    public int getPoints() {
        return points;
    }

    // gets player from playerList
    public Player getPlayer(int playerNo){
        Player currPlayer = (Player) teamPlayers.get(playerNo);
        return currPlayer;
    }

    public Player getActivePlayer(int playerNo){
        Player currPlayer = (Player) activePlayers.get(playerNo);
        return currPlayer;
    }

    // Returns array of team player names
    ArrayList<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public ArrayList<Player> getActivePlayers(){
        return activePlayers;
    }


    // setters

    void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    void setPoints(int points) {
        this.points = points;
    }


    // Increments goals for and goals against
    void updateGoals(int gf, int ga){
        setGoalsFor(getGoalsFor()+gf);
        goalsAgainst += ga;
    }

    // clears active players list from local game
    public void resetActivePlayers(){
        activePlayers = new ArrayList();
    }

    // add active player to activePlayers
    public void addActivePlayer(int playerNo){
        Player player = getPlayer(playerNo);
        activePlayers.add(player);
    }

    // adds player to team
    void addPlayer(Player player) {
        teamPlayers.add(player);
        // updates teamplayersmodel
        teamPlayersModel.add(teamPlayersModel.getSize(),player.getName());
    }

    // Override functions as team wins need to refer increment points also
    @Override
    public void gameWon(){
        incrementGamesWon();
        points += 3;
        Player currPlayer;
        for (int i = 0; i < getActivePlayers().size(); i++){
            currPlayer = (Player) getActivePlayers().get(i);
            // calls Item gameWon function
            currPlayer.gameWon();
        }
    }

    @Override
    public void gameDrawn(){
        incrementGamesDrawn();
        points ++;
        Player currPlayer;
        for (int i = 0; i < getActivePlayers().size(); i++){
            currPlayer = (Player) getActivePlayers().get(i);
            currPlayer.gameDrawn();
        }
    }

    @Override
    public void gameLost(){
        incrementGamesLost();
        Player currPlayer;
        for (int i = 0; i < getActivePlayers().size(); i++){
            currPlayer = (Player) getActivePlayers().get(i);
            currPlayer.gameLost();
        }
    }

}
