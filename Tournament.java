package uk.ac.glos.ct5025.s1804317.footballStats;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import uk.ac.glos.ct5025.s1804317.footballStats.UI.SelectTournamentWindow;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class Tournament extends csv {
    private String tournamentName;
    private ArrayList<Team> tournamentTeams;
    private ArrayList tournamentGameList;

    // Holds all teams within the tournament
    private DefaultListModel tournamentTeamsModel = new DefaultListModel();

    public DefaultListModel getTournamentTeamsModel(){
        return tournamentTeamsModel;
    }

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

    public void addGame(StaticGame game) { tournamentGameList.add(game);}

    public String getTournamentName(){
        try{
            return tournamentName;
        } catch (NullPointerException e) {
            return "No tournament selected";
        }
    }

    public static ArrayList<Tournament> getTournamentList(){
        return tournamentList;
    }

    public ArrayList<Team> getTournamentTeams(){
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

            if ("..".equals(choice)) {
                return;
            } else if ("1".equals(choice)) {
                browseGames();
            } else if ("2".equals(choice)) {
                browseTeams();
            } else if ("3".equals(choice)) {
                browseTournament();
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

    public static void exportTournament(Tournament tournament){
        String path = xmlFilePath + tournament.getTournamentName() + ".xml";
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("tournament");
            document.appendChild(root);
            root.setAttribute("name",tournament.getTournamentName());


            Element teams = document.createElement("teams");
            root.appendChild(teams);

            Element team;
            int teamID = 0;

            // Constructs XML format for each team with corresponding data
            for (Team currTeam : tournament.getTournamentTeams()){
                team = document.createElement("team");
                teams.appendChild(team);

                team.setAttribute("id",Integer.toString(teamID));

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(currTeam.getName()));
                team.appendChild(name);

                Element gamesWon = document.createElement("gameswon");
                gamesWon.appendChild(document.createTextNode(Integer.toString(currTeam.getGamesWon())));
                team.appendChild(gamesWon);

                Element gamesLost = document.createElement("gameslost");
                gamesLost.appendChild(document.createTextNode(Integer.toString(currTeam.getGamesLost())));
                team.appendChild(gamesLost);

                Element gamesDrawn = document.createElement("gamesdrawn");
                gamesDrawn.appendChild(document.createTextNode(Integer.toString(currTeam.getGamesLost())));
                team.appendChild(gamesDrawn);

                Element goalsFor = document.createElement("goalsfor");
                goalsFor.appendChild(document.createTextNode(Integer.toString(currTeam.getGoalsFor())));
                team.appendChild(goalsFor);

                Element goalsAgainst = document.createElement("goalsagainst");
                goalsAgainst.appendChild(document.createTextNode(Integer.toString(currTeam.getGoalsAgainst())));
                team.appendChild(goalsAgainst);

                Element teamPoints = document.createElement("teampoints");
                teamPoints.appendChild(document.createTextNode(Integer.toString(currTeam.getPoints())));
                team.appendChild(teamPoints);

                Element teamPlayers = document.createElement("players");
                team.appendChild(teamPlayers);

                int playerID = 0;
                Element player;

                // Constructs XML format for each player within a team with corresponding data
                for (Player currPlayer : currTeam.getActivePlayers()){
                    player = document.createElement("player");
                    teamPlayers.appendChild(player);

                    player.setAttribute("id",Integer.toString(playerID));

                    name = document.createElement("name");
                    name.appendChild(document.createTextNode(currTeam.getName()));
                    team.appendChild(name);

                    gamesWon = document.createElement("gameswon");
                    gamesWon.appendChild(document.createTextNode(Integer.toString(currTeam.getGamesWon())));
                    team.appendChild(gamesWon);

                    gamesLost = document.createElement("gameslost");
                    gamesLost.appendChild(document.createTextNode(Integer.toString(currTeam.getGamesLost())));
                    team.appendChild(gamesLost);

                    gamesDrawn = document.createElement("gamesdrawn");
                    gamesDrawn.appendChild(document.createTextNode(Integer.toString(currTeam.getGamesLost())));
                    team.appendChild(gamesDrawn);

                    goalsFor = document.createElement("goalsfor");
                    goalsFor.appendChild(document.createTextNode(Integer.toString(currTeam.getGoalsFor())));
                    team.appendChild(goalsFor);

                    playerID++;
                }

                teamID++;
            }

            Element games = document.createElement("games");
            root.appendChild(games);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource,streamResult);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
