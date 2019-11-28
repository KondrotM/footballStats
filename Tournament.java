package uk.ac.glos.ct5025.s1804317.footballStats;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tournament extends csv {
    private String tournamentName;
    private ArrayList tournamentTeams = new ArrayList();
    private ArrayList tournamentGameList = new ArrayList();

    // list of tournaments declared and initialised. all tournaments, teams and players are declared in regards to the tournament list
    public static ArrayList<Tournament> tournamentList = new ArrayList<Tournament>();
    // actively selected tournament
    public static Tournament activeTournament;

    public Tournament(String tempName){
        tournamentName = tempName;
    }

    public void addTeam(Team team){
        tournamentTeams.add(team);
    }

    public void addGame(Game game) { tournamentGameList.add(game);}

    public String getTournamentName(){
        return tournamentName;
    }

    public ArrayList getTournamentTeams() { return tournamentTeams; }

    // creates new tournament
    public static void createTournament(){
        System.out.println(".. Back");
        String tournamentName = main.getInput("Enter tournament name");
        if (tournamentName.equals("..")) {
            return;
        }
        // creates new tournament and appends it to the tournament list
        Tournament tournament1 = new Tournament(tournamentName);
        tournamentList.add(tournament1);
        // if there is only one tournament, it is set as the active one
        if(tournamentList.size()==1) {
            activeTournament = tournamentList.get(0);
        }
    }

    // selects active tournament
    public static void selectTournament(){
        System.out.println("Current active tournament: " + activeTournament.getTournamentName());
        // lists all available tournaments
        System.out.println(".. Back");
        for (int i = 0; i < tournamentList.size(); i++) {
            Tournament currTournament = tournamentList.get(i);
            System.out.println(i+1 + ". " + currTournament.getTournamentName());
        }
        // gets user input and sets it as active tournament
        String currTournament = main.getInput("Select team number");
        if (currTournament.equals("..")){
            return;
        } else {
            int tournamentNumber = Integer.parseInt(currTournament);
            activeTournament = tournamentList.get(tournamentNumber - 1);
        }
    }

    public Team getTeam(int teamNo){
        Team currTeam = (Team) tournamentTeams.get(teamNo);
        return currTeam;
    }

    public void viewTeams() {
        for (int i = 0; i < tournamentTeams.size(); i++) {
            Team currTeam = (Team) tournamentTeams.get(i);
            System.out.println(i + 1 + ". " + currTeam.getTeamName());
        }
    }

    public void browseTeams(){
        while (true) {
            // gets active team to browse
            Team activeTeam = selectTeam();
            // if activeTeam is null, it means the user wants to exit the menu
            // hence, players are only selected if activeTeam is not null
            if (activeTeam != null) {
                activeTeam.selectPlayers();
            } else {
                return;
            }
        }
    }

    public void browse(){
        String choice;
        while (true){
            System.out.println("Browse");
            System.out.println(".. Back");
            System.out.println("1. Games");
            System.out.println("2. Teams");
            choice = main.getInput(null);

            if (choice.equals("..")){
                return;
            } else if (choice.equals("1")){
                browseGames();
            } else if (choice.equals("2")){
                browseTeams();
            }
        }
    }

    // lists all available teams in tournament
    public Team selectTeam() {
        System.out.println(".. Back");
        viewTeams();
        String currTeam = main.getInput(null);
        if (currTeam.equals("..")){
            return null;
        } else {
            int teamNo = Integer.parseInt(currTeam);
            Team activeTeam = getTeam(teamNo -1);
            return activeTeam;
        }


    }

    public void getGame(){
        Team homeTeam = null;
        Team awayTeam = null;

        while (homeTeam == awayTeam) {
            System.out.println("Choose Home Team");
            homeTeam = selectTeam();
            if (homeTeam == null){ return; }
            System.out.println("Choose Away Team");
            awayTeam = selectTeam();
            if (awayTeam == null){ return; }
            if (homeTeam == awayTeam) {
                System.out.println("Teams selected cannot be the same");
            }
        }

        // creates unique set so players can't be added twice
        ArrayList tempHomeTeamPlayers = new ArrayList();
        ArrayList tempAwayTeamPlayers = new ArrayList();


        if (homeTeam.getTeamPlayers().size() > 11) {
            while(tempHomeTeamPlayers.size() < 11) {
                System.out.println("Choose which players will play");
                homeTeam.viewPlayers();
                String currPlayer = main.getInput(null);
                int playerNumber = Integer.parseInt(currPlayer);
                tempHomeTeamPlayers.add(homeTeam.getPlayer(playerNumber));
            }
        } else {
            tempHomeTeamPlayers = homeTeam.getTeamPlayers();
        }
        if (awayTeam.getTeamPlayers().size() > 11) {
            // creates unique set so players can't be added twice
            while(tempAwayTeamPlayers.size() < 11) {
                System.out.println("Choose which players will play");
                awayTeam.viewPlayers();
                String currPlayer = main.getInput(null);
                int playerNumber = Integer.parseInt(currPlayer);
                tempAwayTeamPlayers.add(awayTeam.getPlayer(playerNumber));
            }
        } else {
            tempAwayTeamPlayers = awayTeam.getTeamPlayers();
        }



        // declares new game
        Game currGame = new Game(homeTeam,awayTeam,tempHomeTeamPlayers,tempAwayTeamPlayers);
        // sets the currGame variable to be the completed game
        currGame = currGame.startGame();
        // appends to tournamentGameList
        addGame(currGame);
    }

    public void browseGames() {
        System.out.println("..Back");
//        try {
        viewGames();
        String currGame = main.getInput(null);
        if (currGame.equals("..")){
            return;
        }
//        }
    }

// https://www.mkyong.com/java/java-how-to-list-all-files-in-a-directory/
    public void viewGames() {

        File file = new File(filePath);
        for(String fileNames : file.list()) {
            // changes characters which cannot be encoded into a filename
            fileNames = fileNames.replace("_"," ");
            fileNames = fileNames.replace("%", "/");
            fileNames = fileNames.replace(";",":");
            // removes .csv extension
            fileNames = fileNames.substring(0, fileNames.length() - 4);

            System.out.println(fileNames);
        }

//        try (Stream<Path> walk = Files.walk(Paths.get(Game.getFilePath()))) {
//            ArrayList<String> results = (ArrayList<String>) walk.filter(Files::isDirectory)
//                    .map(x -> x.toString()).collect(Collectors.toList());
//
//            results.forEach(System.out::println);
//
//         } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
