package uk.ac.glos.ct5025.s1804317.footballStats;

import uk.ac.glos.ct5025.s1804317.footballStats.UI.SelectTournamentWindow;

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
    private ArrayList tournamentTeams;
    private ArrayList tournamentGameList;

    // list of tournaments declared and initialised. all tournaments, teams and players are declared in regards to the tournament list
    public static ArrayList<Tournament> tournamentList = new ArrayList<Tournament>();
    // actively selected tournament
    public static Tournament activeTournament;

    public Tournament(String tempName){
        tournamentName = tempName;
        tournamentTeams = new ArrayList();
        tournamentGameList = new ArrayList();
    }

    public void addTeam(Team team){
        tournamentTeams.add(team);
    }

    public void addGame(Game game) { tournamentGameList.add(game);}

    public String getTournamentName(){
        try{
            return tournamentName;
        } catch (NullPointerException e) {
            return "No tournament selected";
        }
    }

    public static ArrayList getTournamentList(){
        return tournamentList;
    }

    public ArrayList getTournamentTeams(){
        return tournamentTeams;
    }


    public static String[] getTournamentTeamsString() {
        String[] nameList = new String[tournamentList.size()];
        Tournament currTournament;
        for (int i = 0; i < tournamentList.size(); i++){
            currTournament = tournamentList.get(i);
            nameList[i] = currTournament.getTournamentName();
        }
        return nameList;
    }

    // creates new tournament
    public static Tournament factoryTournament(String tournamentName){
        Tournament tournament = null;
        if (tournamentName != ""){
            // creates new tournament and appends it to the tournament list
            tournament = new Tournament(tournamentName);
            tournamentList.add(tournament);
            // if there is only one tournament, it is set as the active one
            if(tournamentList.size()==1) {
                selectTournament(0);
            }

        }
        return tournament;
    }

    // selects active tournament
    public static void selectTournament(int tournamentNumber){
//        System.out.println("Current active tournament: " + activeTournament.getTournamentName());
//        // lists all available tournaments
//        System.out.println(".. Back");
//        for (int i = 0; i < tournamentList.size(); i++) {
//            Tournament currTournament = tournamentList.get(i);
//            System.out.println(i+1 + ". " + currTournament.getTournamentName());
//        }
//        // gets user input and sets it as active tournament
//        String currTournament = main.getInput("Select team number");
//        if (currTournament.equals("..")){
//            return;
//        } else {
//            int tournamentNumber = Integer.parseInt(currTournament);
//            activeTournament = tournamentList.get(tournamentNumber - 1);
//        }
        activeTournament = tournamentList.get(tournamentNumber);
        SelectTournamentWindow.getCurrTournament().setText("ACTIVE TOURNAMENT: " + activeTournament.getTournamentName());
        SelectTournamentWindow.getCurrTournament().repaint();
        SelectTournamentWindow.getCurrTournament().revalidate();

    }

    public Team getTeam(int teamNo){
        Team currTeam = (Team) tournamentTeams.get(teamNo);
        return currTeam;
    }

    public void viewTeams() {
        for (int i = 0; i < tournamentTeams.size(); i++) {
            Team currTeam = (Team) tournamentTeams.get(i);
            System.out.println(i + 1 + ". " + currTeam.getName());
        }
    }

    private void browseTeams(){
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
            System.out.println("3. Tournament");
            choice = main.getInput(null);

            switch (choice) {
                case "..":
                    return;
                case "1":
                    browseGames();
                    break;
                case "2":
                    browseTeams();
                    break;
                case "3":
                    browseTournament();
                    break;
            }
        }
    }

    private void browseTournament(){
        System.out.println("Tournament " + activeTournament.getTournamentName());
        System.out.println("Name, GP W D L GF GA GD Pts");
        Team currTeam;
        for (int i = 0; i < activeTournament.getTournamentTeams().size(); i++){
            currTeam = (Team) activeTournament.getTournamentTeams().get(i);
            System.out.println(currTeam.getName() + " "
                    + currTeam.getGamesPlayed() + " "
                    + currTeam.getGamesWon() + " "
                    + currTeam.getGamesLost() + " "
                    + currTeam.getGamesDrawn() + " "
                    + currTeam.getGoalsFor() + " "
                    + currTeam.getGoalsAgainst() + " "
                    + currTeam.getGoalsDifference() + " "
                    + currTeam.getPoints());
        }
        main.getInput("Press enter to continue");
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

    private void browseGames() {
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
    private void viewGames() throws NullPointerException {

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
