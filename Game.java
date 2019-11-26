package uk.ac.glos.ct5025.s1804317.footballStats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class Game extends csv {
    private String gameTitle;
    private Team homeTeam;
    private Team awayTeam;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private String gameDate;

    private boolean gameFinished = false;

    private ArrayList homePlayers;
    private ArrayList awayPlayers;

    public Game (Team tempHomeTeam, Team tempAwayTeam, ArrayList tempHomePlayers, ArrayList tempAwayPlayers){
        homeTeam = tempHomeTeam;
        awayTeam = tempAwayTeam;
        homePlayers = tempHomePlayers;
        awayPlayers = tempAwayPlayers;

        // sets gameDate to be current date.
        // written as a string but ordered as Y/M/D H:M:S so that it can still be ordered
        gameDate = dateFormat.format(new Date());

    }

    private void getGameMenu() {
        System.out.println(homeTeam.getTeamName() + " - "
                + homeTeam.getGoalsScored() + " -- "
                + awayTeam.getGoalsScored() + " - "
                + awayTeam.getTeamName());
        System.out.println("1. Score Goal");
        System.out.println("2. End Game");
        String choice = main.getInput(null);
        if (choice.equals("1")){
            System.out.println(".. Back");
            System.out.println("1. Home Team");
            System.out.println("2. Away Team");
            choice = main.getInput(null);
            if (choice.equals("1")){ homeTeam.scoreGoal(); }
            if (choice.equals("2")){ awayTeam.scoreGoal(); }
        } else if (choice.equals("2")){
            gameFinished = true;
        }

    }



    public Team getHomeTeam(){ return homeTeam; }

    public Team getAwayTeam(){
        return awayTeam;
    }

    public String getGameDate(){
        return gameDate;
    }

    public String getGameTitle(){ return gameTitle; }

    public ArrayList getAwayPlayers() {
        return awayPlayers;
    }

    public ArrayList getHomePlayers() {
        return homePlayers;
    }

    public void writeGame() throws IOException {
        String title = new String(homeTeam.getGoalsScored() + "_-_" + awayTeam.getGoalsScored() + "_-_" + homeTeam.getTeamName() + "_vs_" + awayTeam.getTeamName() + "_-_" + gameDate +".csv");
        title = title.replace(":",";");
        title = title.replace("/","%");
        title = filePath.concat(title);
        FileWriter fw = null;
        System.out.println(title);
        File file = new File(title);
        file.createNewFile();
        try {
            fw = new FileWriter(title);
            fw.append("Team,Goals\n");
            fw.append(homeTeam.getTeamName() + "," + homeTeam.getGoalsScored() + "\n");
            fw.append(awayTeam.getTeamName() + "," + awayTeam.getGoalsScored() + "\n");

        } catch (IOException e){
            System.out.println("Error!");
            e.printStackTrace();
        } finally {
            try {
                fw.flush();
                fw.close();
            } catch (IOException ex){
                System.err.println("An IOException was caught!");
                ex.printStackTrace();
            }
        }
    }

    public Game startGame() {

        // sets title which will be displayed after the game is over
        gameTitle = homeTeam.getTeamName() + " vs " + awayTeam.getTeamName() + " --- " + gameDate;

        System.out.println(homePlayers);


        while (!gameFinished){
            getGameMenu();
        }
        Game gameResult =  this;
        try {
            writeGame();
        } catch(IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return gameResult;
    }

}
