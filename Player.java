package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Player {

    private String playerForename;
    private String playerSurname;
    private String playerDoB;
    private String playerRole;


    //Creates a Player object which can then be used to assign new players
    public Player(String tempFName, String tempDoB){
        playerForename = tempFName;
//        playerSurname = tempSName;
//        playerRole = tempRole;
        playerDoB = tempDoB;
    }

    public String getPlayerName(){
        return playerForename;
    }
    public String getPlayerSurname() { return playerSurname; }
    public String getPlayerRole(){
        return playerRole;
    }
    public String getPlayerDoB(){
        return playerDoB;
    }
    public String getPlayerTeam() {return "";}

    // creates player and assigns them to a team
    public static void createPlayer(){
        // lists all teams within selected tournament
        try {
            ArrayList currTeams = Tournament.activeTournament.getTournamentTeams();


            for (int i = 0; i < currTeams.size(); i++) {
                Team currTeam = (Team) currTeams.get(i);
                System.out.println(i+1 + ". " + currTeam.getTeamName());
            }
            // asks user for selected team number
            System.out.println("Teams");
            System.out.println(".. Back");
            Tournament.activeTournament.viewTeams();
            //        selectTeam();
            String teamTempNo = main.getInput(null);
            if (teamTempNo.equals("..")){
                return;
            } else {
                int teamNo = Integer.parseInt(teamTempNo);
                // asks user for player information
                String playerName = main.getInput("Enter player name");
                String playerDoB = main.getInput("Enter player DoB");
                Player player1 = new Player(playerName, playerDoB);

                // gets selected team from active tournament
                Team currTeam = Tournament.activeTournament.getTeam(teamNo-1);

                // assigns player to selected team
                currTeam.addPlayer(player1);
                System.out.println(player1.getPlayerName());
            }
        } catch (NullPointerException ex){
            System.out.println("There are no teams to add a player into.");
            System.out.println("Please create a team first.");
            main.getInput("Press enter to continue");
        }
    }

    }
