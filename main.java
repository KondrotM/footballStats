package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Scanner;
class main {
    //private static AbstractCollection<Tournament> tournamentList;
    public static ArrayList<Tournament> tournamentList = new ArrayList<Tournament>();

    public static String getInput(String prompt){
        if (prompt != null) {
            System.out.println(prompt);
        }
        Scanner cmd = new Scanner(System.in);
        return cmd.nextLine();
    }

    public static void getMainMenu(){
        System.out.println("FOOTBALL STATS");
        System.out.println("MAIN MENU");
        System.out.println("1. Create Tournament");
        System.out.println("2. Create Team");
        System.out.println("3. Create Player");
    }

    public static void createPlayer(){
        String playerName = getInput("Enter player name");
        String playerDoB = getInput("Enter player DoB");
        Player player1 = new Player(playerName, playerDoB);
        Tournament currTournament = tournamentList.get(0);
        Team currTeam = currTournament.getTeam(0);
        currTeam.addPlayer(player1);
        System.out.println(player1.getPlayerName());
    }

    public static void createTeam(){
        String teamName = getInput("Enter team name");
        Team team1 = new Team(teamName);
        Tournament currTournament = tournamentList.get(0);
        currTournament.addTeam(team1);
    }

    public static void createTournament(){
        String tournamentName = getInput("Enter tournament name");
        Tournament tournament1 = new Tournament(tournamentName);
        tournamentList.add(tournament1);

        Tournament currTournament = (Tournament) tournamentList.get(0);
        System.out.println(currTournament.getTournamentName());
    }


    public static void main(String args[]) {
        while(true){
            getMainMenu();
            String choice = getInput(null);
            if (choice.equals("1")){
                createTournament();
            } else if (choice.equals("2")) {
                createTeam();
            } else if (choice.equals("3")) {
                createPlayer();
            }
            //System.out.println(getInput(null));
        }

//        Scanner in = new Scanner(System.in);
//        String name = in.nextLine();
//        String role = in.nextLine();
////        String name = in.nextLine();
////        String name = in.nextLine();
//
//
//        Team team1 = new Team("Chelsea");
//        Player player1 = new Player(name,role);
////        Player player1 = new Player("Martin","V","Defence","20/2/1995");
////        Player player2 = new Player("Alex","S","Striker","20/2/1995");
//        team1.addPlayer(player1);
//        Player currPlayer = (Player) team1.getTeamPlayers().get(0);
//        System.out.println(currPlayer.getPlayerName());

// comment
    }}

