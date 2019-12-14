//Add playerID so that there aren't duplicates

package uk.ac.glos.ct5025.s1804317.footballStats;

import uk.ac.glos.ct5025.s1804317.footballStats.UI.Create.CreateTeamWindow;

import javax.swing.*;
import java.util.ArrayList;

public class Team extends Item {
    private ArrayList<Player> teamPlayers;
    private ArrayList<Player> activePlayers;

    // Holds all players to show within UI
    private DefaultListModel teamPlayersModel;

    public DefaultListModel teamActivePlayersModel;

    public DefaultListModel getTeamPlayersModel() {
        return teamPlayersModel;
    }

    public DefaultListModel getTeamActivePlayersModel() {
        return teamActivePlayersModel;
    }


    private int goalsAgainst;
    private int points;


    public Team(String tempName) {
        super(tempName);

        teamPlayersModel = new DefaultListModel();
        teamActivePlayersModel = new DefaultListModel();

        //declares an Array to store the team's players
        teamPlayers = new ArrayList();
        // declares an Array to store active players for future games
        activePlayers = new ArrayList();

        goalsFor = 0;
        goalsAgainst = 0;
        points = 0;
    }

    @Override
    public void gameWon(){
        gamesWon++;
        points += 3;
        Player currPlayer;
        for (int i = 0; i < getActivePlayers().size(); i++){
            currPlayer = (Player) getActivePlayers().get(i);
            currPlayer.gameWon();
        }
    }

    @Override
    public void gameDrawn(){
        gamesDrawn++;
        points ++;
        Player currPlayer;
        for (int i = 0; i < getActivePlayers().size(); i++){
            currPlayer = (Player) getActivePlayers().get(i);
            currPlayer.gameDrawn();
        }
    }

    @Override
    public void gameLost(){
        gamesLost++;
        Player currPlayer;
        for (int i = 0; i < getActivePlayers().size(); i++){
            currPlayer = (Player) getActivePlayers().get(i);
            currPlayer.gameLost();
        }
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalsDifference() {
        int goalsDifference = goalsFor - goalsAgainst;
        return goalsDifference;
    }

    public int getPoints() {
        return points;
    }

    public void updateGoals(int gf, int ga){
        goalsFor += gf;
        goalsAgainst += ga;
    }

//    public void gameWon() {
//        gamesWon++;
//        points += 3;
//    }

//    public void gameDrawn(){
//        gamesDrawn++;
//        points ++;
//    }

    public ArrayList<Player> getTeamPlayers() {
        //Returns array of team player names
        return teamPlayers;
    }

    public ArrayList<Player> getActivePlayers(){
        return activePlayers;
    }

    public void resetActivePlayers(){
        activePlayers = new ArrayList();
    }

    public void addActivePlayer(int playerNo){
        Player player = getPlayer(playerNo);
        activePlayers.add(player);
    }

    public void addActivePlayers(ArrayList playerList){
        activePlayers = playerList;
    }
    // creates team within active tournament
    public static Team createTeam(String teamName){
        Team team = new Team(teamName);
        try {
            Tournament.activeTournament.addTeam(team);
            DefaultListModel model = Tournament.activeTournament.getTournamentTeamsModel();
            model.add(model.getSize(),team.getName());
            return team;
        } catch (NullPointerException ex) {
            JLabel msg = CreateTeamWindow.getErrorMessage();
            msg.setText("<html><center>There is no tournament for the team to be created into<br/>Please create a tournament first</center></html>");
            return null;
        }
    }

    public void addPlayer(Player player) {
        teamPlayers.add(player);
        teamPlayersModel.add(teamPlayersModel.getSize(),player.getName());
    }


    public void viewPlayers(){
        for (int i = 0; i < teamPlayers.size(); i++){
            Player currPlayer = (Player) teamPlayers.get(i);
            System.out.println(i+1 + ". " + currPlayer.getName());
        }
    }

    public Player getActivePlayer(int playerNo){
        Player currPlayer = (Player) activePlayers.get(playerNo);
        return currPlayer;
    }

    public Player getPlayer(int playerNo){
        Player currPlayer = (Player) teamPlayers.get(playerNo);
        return currPlayer;
    }

    public void selectPlayers(){
        //lists all available players in team
        System.out.println("Players for "+ getName());
        System.out.println(".. Back");
        viewPlayers();
        String currPlayer = main.getInput(null);
        if (currPlayer.equals("..")){
            return;
        } else {
            Player player = getPlayer(Integer.parseInt(currPlayer)-1);
            System.out.println("Name: " + player.getName());
            System.out.println("Date of Birth: " +player.getPlayerDoB());
        }
    }
}
