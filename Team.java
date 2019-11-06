//Add playerID so that there aren't duplicates

package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Team {
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

    public static void main(String[] args) {


    }
}
