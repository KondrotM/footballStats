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
