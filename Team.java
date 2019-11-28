//Add playerID so that there aren't duplicates

package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Team extends Item{
    private String teamName;
    private ArrayList teamPlayers;

    public Team(String tempName) {
        super(tempName);
        //declares an Array to store the team's players
        teamPlayers = new ArrayList();
    }

    // creates team within active tournament
    public static void createTeam(){
        System.out.println(".. Back");
        String teamName = main.getInput("Enter team name");
        if (teamName.equals("..")){
            return;
        }
        Team team1 = new Team(teamName);
        try {
            Tournament.activeTournament.addTeam(team1);
        } catch (NullPointerException ex) {
            System.out.println("There is no tournament for the team to be created into");
            System.out.println("Please create a tournament first");
            main.getInput("Press enter to continue");
        }
    }

    public void addPlayer(Player player) {
        //Grabs the player's name from the Player class, and adds it to the team's players.
        teamPlayers.add(player);
    }

    public ArrayList getTeamPlayers() {
        //Returns array of team player names
        return teamPlayers;
    }

    public void viewPlayers(){
        for (int i = 0; i < teamPlayers.size(); i++){
            Player currPlayer = (Player) teamPlayers.get(i);
            System.out.println(i+1 + ". " + currPlayer.getName());
        }
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
