package uk.ac.glos.ct5025.s1804317.footballStats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Game extends csv {
    private String gameTitle;
    private Team homeTeam;
    private Team awayTeam;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private String gameDate;

    private boolean gameFinished = false;


    public Game (Team tempHomeTeam, Team tempAwayTeam){
        homeTeam = tempHomeTeam;
        awayTeam = tempAwayTeam;

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

    public static void getGame(){
        Team homeTeam = null;
        Team awayTeam = null;

        while (homeTeam == awayTeam) {
            System.out.println("Choose Home Team");
            homeTeam = Tournament.activeTournament.selectTeam();
            if (homeTeam == null){ return; }
            System.out.println("Choose Away Team");
            awayTeam = Tournament.activeTournament.selectTeam();
            if (awayTeam == null){ return; }
            if (homeTeam == awayTeam) {
                System.out.println("Teams selected cannot be the same");
            }
        }

        // creates unique set so players can't be added twice
        ArrayList tempHomeTeamPlayers = new ArrayList();
        ArrayList tempAwayTeamPlayers = new ArrayList();


        if (homeTeam.getTeamPlayers().size() > 11) {
            while(homeTeam.getActivePlayers().size() < 11) {
                System.out.println("Choose which players will play");
                homeTeam.viewPlayers();
                String currPlayer = main.getInput(null);
                if (currPlayer.equals("src/test")){
                    return;
                }
                int playerNumber = Integer.parseInt(currPlayer);
                homeTeam.addActivePlayer(homeTeam.getPlayer(playerNumber));
            }
        } else {
            homeTeam.addActivePlayers(homeTeam.getTeamPlayers());
        }
        if (awayTeam.getTeamPlayers().size() > 11) {
            // creates unique set so players can't be added twice
            while(homeTeam.getActivePlayers().size() < 11) {
                System.out.println("Choose which players will play");
                awayTeam.viewPlayers();
                String currPlayer = main.getInput(null);
                if (currPlayer.equals(".")){
                    return;
                }
                int playerNumber = Integer.parseInt(currPlayer);
                awayTeam.addActivePlayer(awayTeam.getPlayer(playerNumber));
            }
        } else {
            awayTeam.addActivePlayers(awayTeam.getTeamPlayers());
        }

        // declares new game
        Game currGame = new Game(homeTeam,awayTeam);
        // sets the currGame variable to be the completed game
        currGame = currGame.startGame();
        // appends to tournamentGameList
        Tournament.activeTournament.addGame(currGame);
    }

    private Game startGame() {

        initialiseGame();

        while (!gameFinished){
            getGameMenu();
        }

        Game gameResult =  this;

        writeGame();
        finishGame();
        homeTeam.resetActivePlayers();
        awayTeam.resetActivePlayers();

        return gameResult;
    }

    private void finishGame(){
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
    }

    private void getGameMenu() {
        System.out.println(homeTeam.getName() + " - "
                + homeTeam.getGoals() + " -- "
                + awayTeam.getGoals() + " - "
                + awayTeam.getName());
        System.out.println("1. Score Goal");
        System.out.println("2. End Game");
        String choice = main.getInput(null);
        if (choice.equals("1")){
            System.out.println(".. Back");
            System.out.println("1. Home Team");
            System.out.println("2. Away Team");
            choice = main.getInput(null);
            if (choice.equals("1")){
                homeTeam.scoreGoal();
                choosePlayer(homeTeam);
            }
            if (choice.equals("2")){
                awayTeam.scoreGoal();
                choosePlayer(awayTeam);
            }
        } else if (choice.equals("2")){
            gameFinished = true;
        }

    }

    private void choosePlayer(Team team) {
        Player player = (Player) team.getElement(team.getActivePlayers());
        if (player == null) {
            return;
        } else {
            player.scoreGoal();
        }
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

    private void initialiseGame(){
        // sets title
        gameTitle = homeTeam.getName() + " vs " + awayTeam.getName() + " --- " + gameDate;

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
