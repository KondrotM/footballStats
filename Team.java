//Add playerID so that there aren't duplicates

package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Team extends Item{
    private String teamName;
    private ArrayList teamPlayers;

    public Team(String tempName) {
        teamName = tempName;
        //declares an Array to store the team's players
        teamPlayers = new ArrayList();
    }

    public void addPlayer(Player player) {
        //Grabs the player's name from the Player class, and adds it to the team's players.
        teamPlayers.add(player);
    }

    public String getTeamName(){
        return teamName;
    }

    public ArrayList getTeamPlayers() {
        //Returns array of team player names
        return teamPlayers;
    }

    public void viewPlayers(){
        for (int i = 0; i < teamPlayers.size(); i++){
            Player currPlayer = (Player) teamPlayers.get(i);
            System.out.println(i+1 + ". " + currPlayer.getPlayerName());
        }
    }

    public Player getPlayer(int playerNo){
        Player currPlayer = (Player) teamPlayers.get(playerNo);
        return currPlayer;
    }
}
