package uk.ac.glos.ct5025.s1804317.footballStats;

import uk.ac.glos.ct5025.s1804317.footballStats.UI.GameTimer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Game extends csv {
    private String gameTitle;
    private Team homeTeam;
    private Team awayTeam;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private GameTimer gt = new GameTimer();

    private String gameDate;

    private boolean gameFinished = false;

    public GameTimer getGameTimer(){
        return gt;
    }

    private Timeline timeLine;

    private boolean possession;

    public Timeline getTimeLine(){
        return timeLine;
    }

    public Game (Team tempHomeTeam, Team tempAwayTeam){
        homeTeam = tempHomeTeam;
        awayTeam = tempAwayTeam;
        possession = true;

        // initialises a new timeline and passes this game as a parameter
        timeLine = new Timeline(this);

        // sets gameDate to be current date.
        // written as a string but ordered as Y/M/D H:M:S so that it can still be ordered
        gameDate = dateFormat.format(new Date());
    }

    public Team getHomeTeam(){ return homeTeam; }

    public Team getAwayTeam(){
        return awayTeam;
    }

    public String getGameDate(){
        return gameDate;
    }

    public String getGameTitle(){ return gameTitle; }

    public boolean getPossession(){
        return possession;
    }

    public void changePossession(){
        possession = !possession;
        System.out.println(possession);
    }

    private Game startGame() {

        initialiseGame();

        while (!gameFinished){
//            getGameMenu();
        }

        Game gameResult =  this;

        writeGame();
        finishGame();


        return gameResult;
    }

    public void finishGame(){
        if (homeTeam.getGoals() > awayTeam.getGoals()){
            homeTeam.gameWon();
            awayTeam.gameLost();
        } else if (awayTeam.getGoals() > homeTeam.getGoals()) {
            awayTeam.gameWon();
            homeTeam.gameLost();
        } else {
            awayTeam.gameDrawn();
            homeTeam.gameDrawn();
        }
        homeTeam.updateGoals(homeTeam.getGoals(),awayTeam.getGoals());
        awayTeam.updateGoals(awayTeam.getGoals(), homeTeam.getGoals());

        homeTeam.resetActivePlayers();
        awayTeam.resetActivePlayers();
    }

    private void writeGame() {
        String title = gameDate + "_-_" + homeTeam.getName() + "_vs_" + awayTeam.getName() + ".csv";
        title = title.replace(":",";");
        title = title.replace("/","%");
        title = filePath.concat(title);
        FileWriter fw = null;
        System.out.println(title);
        File file = new File(title);
        try {
        file.createNewFile();
        } catch ( IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        try {
            fw = new FileWriter(title);
            fw.append("Team,Goals\n");
            // repeat .append() calls instead of "+" to save on extra StringBuilder allocation
            // https://bukkit.org/threads/stringbuilder-warning.457503/
            fw.append(homeTeam.getName()).append(",").append(String.valueOf(homeTeam.getGoals())).append("\n");
            fw.append(awayTeam.getName()).append(",").append(String.valueOf(awayTeam.getGoals())).append("\n");
            Player player;
            for (int i = 0; i < homeTeam.getActivePlayers().size(); i++){
                player = (Player) homeTeam.getActivePlayers().get(i);
                //separates home and away players with 'h' and 'a' in the csv file.
                fw.append("h"+player.getName()+","+player.getGoals()+"\n");
            }
            for (int i = 0; i < awayTeam.getActivePlayers().size(); i++){
                player = (Player) awayTeam.getActivePlayers().get(i);
                fw.append("a"+player.getName()+","+player.getGoals()+"\n");
            }

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

    public void initialiseGame(){
        // Flushes out local player data
        Player player;
        homeTeam.init();
        for (int i = 0; i < homeTeam.getActivePlayers().size(); i++){
            player = (Player) homeTeam.getActivePlayers().get(i);
            player.init();
        }
        awayTeam.init();
        for (int i = 0; i < awayTeam.getActivePlayers().size(); i++){
            player = (Player) awayTeam.getActivePlayers().get(i);
            player.init();
        }

    }



}
