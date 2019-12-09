package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Player extends Element {

    private String playerDoB;



    // shows which team player belongs to
    private Team playerTeam;

    //Creates a Player object which can then be used to assign new players
    public Player(String tempFName, String tempDoB, Team team){
        super(tempFName);
        playerDoB = tempDoB;
        playerTeam = team;
    }

    public void addToTeam(){
        playerTeam.addPlayer(this);
    }

    public String getPlayerDoB(){
        return playerDoB;
    }

    // creates player and assigns them to a team
    public static void createPlayer(){
        // lists all teams within selected tournament
        try {
            ArrayList currTeams = Tournament.activeTournament.getTournamentTeams();


            for (int i = 0; i < currTeams.size(); i++) {
                Team currTeam = (Team) currTeams.get(i);
                System.out.println(i+1 + ". " + currTeam.getName());
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

                // gets selected team from active tournament
                Team currTeam = Tournament.activeTournament.getTeam(teamNo-1);

                // creates player
                Player player1 = new Player(playerName, playerDoB, currTeam);

                // assigns player to selected team
                currTeam.addPlayer(player1);
                System.out.println(player1.getName());
            }
        } catch (NullPointerException ex){
            System.out.println("There are no teams to add a player into.");
            System.out.println("Please create a team first.");
            main.getInput("Press enter to continue");
        }
    }

    @Override
    public void scoreGoal(){
        goals++;
        goalsFor++;
    }

    }
